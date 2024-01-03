package com.medical.record.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class AESUtils {

    /**
     * AES 的 key 只支持16，24或者32位
     */
    public static String KEY = "0123456789abcdef";

    /**
     * AES 加密
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc) throws Exception {
        return encrypt(sSrc, KEY);
    }

    public static String encrypt(String sSrc, String key) throws Exception {
        byte[] raw = KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * AES解密
     * @param sSrc
     * @return
     */
    public static String decryptRequest(String sSrc){
        return decrypt(sSrc, "abcdefghijklmnop");
    }

    public static String decrypt(String sSrc, String key){
        try {
            byte[] raw = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, StandardCharsets.UTF_8);
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>(Comparator.naturalOrder());
        map.put("pid","1000");
        map.put("type","alipay");
        map.put("out_trade_no","12412512524324324");
        map.put("notify_url","test");
        map.put("return_url","test");
        map.put("name","测试");
        map.put("money","1.00");
        StringBuilder sb = new StringBuilder("");
        map.forEach((k,v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}

