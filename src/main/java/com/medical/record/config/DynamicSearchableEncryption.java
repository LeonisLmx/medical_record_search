package com.medical.record.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.medical.record.mapper.EncryptRecordMapper;
import com.medical.record.model.EncryptRecord;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author urey.liu
 * @description 动态查询加密算法
 * @date 2023/5/23 5:35 下午
 */
@Component
public class DynamicSearchableEncryption {

    @Resource
    private EncryptRecordMapper encryptRecordMapper;

    public static final Logger log = LoggerFactory.getLogger(DynamicSearchableEncryption.class);

    private static final String SYMMETRIC_ALGORITHM = "AES";
    private static final String ASYMMETRIC_ALGORITHM = "RSA";
    private static final String AES_KEY = "0123456789abcdef";

    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    private static SecretKey symmetricKey;

    @PostConstruct
    public void init(){
        Security.addProvider(new BouncyCastleProvider());
        try {
            setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setup() throws Exception {
        publicKey = getPublicKeyFromPem();
        privateKey = getPrivateKeyFromPem();

        // 生成对称密钥
        symmetricKey = new SecretKeySpec(AES_KEY.getBytes(), SYMMETRIC_ALGORITHM);
        log.info("init aes key success!");
    }

    public static PrivateKey getPrivateKeyFromPem() throws Exception {
//        byte[] privateKeyBytes = Files.readAllBytes(Paths.get("/root/java/private_key.der"));
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(new ClassPathResource("private_key.der").getURI()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    public static PublicKey getPublicKeyFromPem() throws Exception {
//        byte[] publicKeyBytes = Files.readAllBytes(Paths.get("/root/java/public_key.der"));
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(new ClassPathResource("public_key.der").getURI()));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return kf.generatePublic(keySpec);
    }

    public String encrypt(String plaintext) throws Exception {
        return this.encrypt(plaintext, true);
    }

    public String encryptRequest(String plaintext) throws Exception {
        return this.encrypt(plaintext, false);
    }

    public String encrypt(String plaintext, boolean save) throws Exception {
        // 使用对称密钥加密数据
        Cipher symmetricCipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
        symmetricCipher.init(Cipher.ENCRYPT_MODE, symmetricKey);
        byte[] encryptedData = symmetricCipher.doFinal(plaintext.getBytes());

        // 使用公钥加密对称密钥
        Cipher asymmetricCipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);
        asymmetricCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKey = asymmetricCipher.doFinal(symmetricKey.getEncoded());

        // 计算哈希值作为搜索索引
        HashFunction hashFunction = Hashing.sha256();
        HashCode hashCode = hashFunction.hashBytes(encryptedData);

        String hashId = hashCode.toString();

        if (save) {
            // 将加密的数据、加密的密钥和哈希值存储在数据库中
            EncryptRecord record = new EncryptRecord();
            record.setEncryptKey(Base64.getEncoder().encodeToString(encryptedKey));
            record.setEncryptData(Base64.getEncoder().encodeToString(encryptedData));
            record.setHashId(hashId);
            encryptRecordMapper.insert(record);
        }
        return hashId;
    }

    public String decrypt(String hashId) throws Exception {
        EncryptRecord record = encryptRecordMapper.selectList(new QueryWrapper<EncryptRecord>().eq("hash_id",hashId)).get(0);
        // 使用私钥解密加密的密钥
        Cipher asymmetricCipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);
        asymmetricCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedKey = asymmetricCipher.doFinal(Base64.getDecoder().decode(record.getEncryptKey()));

        // 使用解密的密钥解密数据
        Cipher symmetricCipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
        symmetricCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptedKey, SYMMETRIC_ALGORITHM));
        byte[] decryptedData = symmetricCipher.doFinal(Base64.getDecoder().decode(record.getEncryptData()));

        return new String(decryptedData);
    }
}
