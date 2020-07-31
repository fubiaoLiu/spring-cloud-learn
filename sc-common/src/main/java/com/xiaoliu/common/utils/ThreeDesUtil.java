package com.xiaoliu.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @description: 3DES算法加密、解密
 * @author: liufb
 * @create: 2020/7/31 9:39
 **/
public class ThreeDesUtil {
    /**
     * 加密
     *
     * @param data 数据明文
     * @param key  密钥
     * @return 密文
     */
    public static String encrypt(String data, String key) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            byte[] b = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            String ss = new String(Base64.encodeBase64(b));
            ss = ss.replaceAll("\\+", "-");
            ss = ss.replaceAll("/", "_");
            return ss;
        } catch (Exception ex) {
            ex.printStackTrace();
            return data;
        }
    }

    /**
     * 解密
     *
     * @param data 数据密文
     * @param key  密钥
     * @return 数据明文
     */
    public static String decrypt(String data, String key) {
        try {
            data = data.replaceAll("-", "+");
            data = data.replaceAll("_", "/");
            byte[] bytesrc = Base64.decodeBase64(data.getBytes(StandardCharsets.UTF_8));
            // --解密的key
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            // --Chipher对象解密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            byte[] retByte = cipher.doFinal(bytesrc);

            return new String(retByte, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            return data;
        }
    }
}
