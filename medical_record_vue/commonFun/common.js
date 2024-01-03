export default {
    aesEncrypt(str) {
        var APIFMS;
        const key = 'abcdefghijklmnop'
        try {
            var keyHex_encrypt = CryptoJS.enc.Utf8.parse(key);
            var encrypted = CryptoJS.AES.encrypt(str, keyHex_encrypt, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            APIFMS = CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
        } catch (err) {
            console.log('aes 加密 -------------------------');
            console.log(err);
        }
        return APIFMS;
    },

    /**
     * 解密字符串
     * @param str 要解密的字符串
     * @returns {string}
     */
    aesDecrypt(str) {
        var result_value;
        const key = '0123456789abcdef'
        try {
            var keyHex_decrypt = CryptoJS.enc.Utf8.parse(key);
            var decrypted = CryptoJS.AES.decrypt({
                ciphertext: CryptoJS.enc.Base64.parse(str)
            }, keyHex_decrypt, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            result_value = decrypted.toString(CryptoJS.enc.Utf8);
        } catch (err) {
            console.log('aes 解密 ------------------------- ');
            console.log(err);
            console.log("解密前数据：" + str);
        }
        return result_value;
    }
}