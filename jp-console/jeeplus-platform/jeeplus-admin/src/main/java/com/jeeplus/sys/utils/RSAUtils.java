package com.jeeplus.sys.utils;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import com.alibaba.fastjson2.JSONObject;


import com.jeeplus.core.errors.ErrorConstants;
import org.springframework.util.Base64Utils;


import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

public class RSAUtils {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    private static final String ALGORITHM_NAME = "RSA";
    private static final String MD5_RSA = "MD5withRSA";

    /**
     * 获取密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
        generator.initialize(2048);
        return generator.generateKeyPair();
    }
    /**
     * 获取base64加密后密钥对
     */
    public static JSONObject getkey (){
        JSONObject result = new JSONObject();
        try {
            KeyPairGenerator generator = null;
            generator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            result.put("privateKey", privateKey);
            result.put("publicKey", publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 获取公钥
     *
     * @param publicKey base64加密的公钥字符串
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 获取私钥
     *
     * @param privateKey base64加密的私钥字符串
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        return keyFactory.generatePrivate(keySpec);
    }
    /**
     * RSA公钥加密
     *
     * @param data      待加密数据
     * @param publicKeyStr 公钥
     */
    public static String publicEncrypt(String data, String publicKeyStr) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64(encryptedData));
    }

    /**
     * RSA私钥加密
     *
     * @param data      待加密数据
     * @param privateKeyStr 私钥
     */
    public static String privateEncrypt(String data, String privateKeyStr)  {
        byte[] encryptedData = null;
        try{
            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            int inputLen = data.getBytes().length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_ENCRYPT_BLOCK;
            }
            encryptedData = out.toByteArray();
            out.close();
        }catch (Exception e){

            e.printStackTrace();
        }


        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64(encryptedData));
    }

    /**
     * RSA公钥解密
     *
     * @param data       待解密数据
     * @param publicKeyStr 公钥
     */
    public static String publicDecrypt(String data, String publicKeyStr)  {
        byte[] decryptedData =  null;
        try{
            PublicKey publicKey = getPublicKey(publicKeyStr);
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] dataBytes = Base64.decodeBase64(data);
            int inputLen = dataBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_DECRYPT_BLOCK;
            }
            decryptedData = out.toByteArray();
            out.close();
        } catch (BadPaddingException e){
            throw new RuntimeException ( ErrorConstants.ERROR_PARAM_ERROR );
        } catch (Exception e){
            e.printStackTrace();
        }
        // 解密后的内容
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    /**
     * RSA私钥解密
     *
     * @param data       待解密数据
     * @param privateKeystr 私钥
     */
    public static String privateDecrypt(String data, String privateKeystr)  {
        PrivateKey privateKey = null;
        byte[] decryptedData =null;
        try {
            privateKey = getPrivateKey(privateKeystr);
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] dataBytes = Base64.decodeBase64(data);
            int inputLen = dataBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_DECRYPT_BLOCK;
            }
            decryptedData = out.toByteArray();
            out.close();
        } catch (BadPaddingException e){
            throw new RuntimeException ( ErrorConstants.ERROR_PARAM_ERROR );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 解密后的内容
        return new String(decryptedData, StandardCharsets.UTF_8);
    }







    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(MD5_RSA);
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(MD5_RSA);
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }







//    //获取公钥私钥
//    public  static JSONObject getkey(){
//
//        RSA rsa = new RSA();
//
//        String privateKeyBase64 = rsa.getPrivateKeyBase64();
//        String publicKeyBase64 = rsa.getPublicKeyBase64();
//        JSONObject result = new JSONObject();
//        result.put("privateStr",privateKeyBase64);
//        result.put("publicStr",publicKeyBase64);
//        return result;
//    }
//
//    //公钥加密
//    public static String publicEncrypt(String data,String PUBLIC_KEY){
//        RSA rsa = new RSA(null, PUBLIC_KEY);
//
//        //byte[] aByte = SecureUtil.decode(data);
//        byte[] encrypt = rsa.encrypt(data, KeyType.PublicKey);
//        return  new String(Base64Utils.encode(encrypt));
//    }
//
//    //私钥加密
//    public static String privateEncrypt(String data,String PRIVATE_KEY){
//        RSA rsa = new RSA(PRIVATE_KEY, null);
//        byte[] encrypt = rsa.encrypt(data, KeyType.PrivateKey);
//        return new String(Base64Utils.encode(encrypt));
//    }
//
//    //公钥解密
//    public static String publicDecrypt(String data,String PUBLIC_KEY){
//        RSA rsa = new RSA(null, PUBLIC_KEY);
//        byte[] aByte = SecureUtil.decode(data);
//        byte[] encrypt =  rsa.decrypt(aByte, KeyType.PublicKey);
//
//        return  new String(encrypt);
//    }
//
    //私钥解密
//    public static String privateDecrypt(String data,String PRIVATE_KEY){
//        RSA rsa = new RSA(PRIVATE_KEY, null);
//        byte[] aByte = SecureUtil.decode(data);
//        byte[] encrypt =  rsa.decrypt(aByte, KeyType.PrivateKey);
//        return   new String(encrypt);
//    }



//    public static void main(String[] args) {
//        String data = "{\"username\":\"张三\",\"password\":\"test6666!\",\"code\":\"ebpg\",\"uuid\":\"c13cd5f2-812f-4bac-baf7-6cd9ad1882f8\",\"id\":\"1669185275541516289\",\"name\":\"系统管理员\",\"sign\":\"hhh\",\"companyDTO\":{\"name\":\"组织机构\"},\"officeDTO\":{\"name\":\"研发部\"},\"remarks\":\"sss\"}";
//        JSONObject aaa = getkey();
//        String privateStr = aaa.getString("privateStr");
//        privateStr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKPIIcsNGTGIZusQtho4feATGfdJYW8VRkjQy75jrz2lfIVebpqBYRNKtf0gljjNBw8bRm9VhxZXCkNRVFKfadu+1eIjmlp7Jf5d2aK0RMJuuB+3qSAtLNspxnYUIi8KnmOjLAhwzsrezGycH9wRJkZKbysD8a2XwDU6ueaFWoh9AgMBAAECgYEAh7b02a7262ad8bcdTsj09VKet5zk0gmM/hWWXyN3ONwCPPmtKPaAnF3SQ/hWwqaSbOArE2KJ1+c0J+giuXxDWHDkIWffAywFFZ4vlJMrtZXMJZgZS1hZK9KG3R0W6Hg5SwAL/UiuD3sh3k47L8F0yDWlKjpjG9VPqIhppC1XhnkCQQDh2lV61ZFUeQnACWEd4vG/472QBA8jrsKtac1GH3RSi7amREHGKTWtQUWyTLQ5urvccQ9OWPP+IfJRRa45m+hfAkEAuaTA3MWZ37pweey3RjkyDuccQ3Z5yNK2Lk3ImxSIGC8FJxwwlAXGx4wIDgzAbnlOqcdjbkHyuQGwvTA9p+LsowJBAK9toMHVi5n6niks3+OP+MldA0wO6xeDUjHSoaCbJAZOAWRqVlCuh8P4lENnMwI4Wl1i8Emh7Ht0ML0SC78xq68CQECKBoK6L8SYDIJWyWCggcaskibiCXTHzI5MfHFtseK2dhQiuMC9QV4eUK6RwFSn17EuoGK0iq3G6KrdGpJDUgECQGanWPlokAi6EXs8rzOh9p7rR1JE7n/Jv+0K0ewRMwx0lGMSHtiJHSgDhXVp72ASiRTnTiNYhcqvuTrXlnSuKM0=";
//        String publicStr = aaa.getString("publicStr");
//        publicStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjyCHLDRkxiGbrELYaOH3gExn3SWFvFUZI0Mu+Y689pXyFXm6agWETSrX9IJY4zQcPG0ZvVYcWVwpDUVRSn2nbvtXiI5paeyX+XdmitETCbrgft6kgLSzbKcZ2FCIvCp5joywIcM7K3sxsnB/cESZGSm8rA/Gtl8A1OrnmhVqIfQIDAQAB";
//
//
//        String xsd = "urUkVrZqveFqNRo9QnaYvm4dLomOAMZReS18NsdrGwc3HvkLDPHX3nloiEOsOxUSmdjYBW/MrBJWXFpVxbWK8NmGRQ/UviI49NO6noxY3FvmAkOc9x8uGDNbK42bin/eGCcy2RqdPz65gY2KMnWyR6eSBg784930UKwsL834+Ws=";
//        String xx = decodeStr("urUkVrZqveFqNRo9QnaYvm4dLomOAMZReS18NsdrGwc3HvkLDPHX3nloiEOsOxUSmdjYBW/MrBJWXFpVxbWK8NmGRQ/UviI49NO6noxY3FvmAkOc9x8uGDNbK42bin/eGCcy2RqdPz65gY2KMnWyR6eSBg784930UKwsL834+Ws=");
//        String bbb = publicEncrypt(data,publicStr);
        //bbb="Osd6xywpI7L02who7cwNLrrWp8JCXWeY5AJIhHhkvXnfyoc6n6vxMBp/ch+EES1/SUtD2VUM+YDAQmV7KFU8OqTwf59ilyRmrsqWgJjtwkE0x2B78bJFsr9q1Ei4gmM37fpcpRhAgmoAZCZFJKJGl3q4mHXW9Wq4iDrNcHXhqMg=";
//        String ccc= privateDecrypt(bbb,privateStr);
//        System.out.println(ccc);

//        String ddd = privateEncrypt(data,PRIVATE_KEY);
  //      ddd="UbO/sOyJTSMwAPMQcE0RqeDCGbmR+RRaM2rPsnPqkpNdnb4ueW1mytUJDlT+W0kukx6BDoLv53hmRYTS6ZYrfcYpTdjVvo1p4BZYrLPpvjykY0uzWyYOOBqqXMFJBE4v9B3FlvfaaUxV4e86vcK5UsmfqrTbpaQUtINO8C/q0nQ=";
  //      ddd="UbO/sOyJTSMwAPMQcE0RqeDCGbmR+RRaM2rPsnPqkpNdnb4ueW1mytUJDlT+W0kukx6BDoLv53hmRYTS6ZYrfcYpTdjVvo1p4BZYrLPpvjykY0uzWyYOOBqqXMFJBE4v9B3FlvfaaUxV4e86vcK5UsmfqrTbpaQUtINO8C/q0nQ=";
//        String eee= publicDecrypt(ddd,PUBLIC_KEY);
//        System.out.println(eee);
//    }



    public static void main(String[] args) {
        try {

            // 生成密钥对
            //KeyPair keyPair = getKeyPair();
            //String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            //String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            //System.out.println("私钥 => " + privateKey + "\n");
            //System.out.println("公钥 =>" + publicKey + "\n");

           JSONObject keyPairMap = getkey();
            String privateKey = keyPairMap.getString("privateKey");
            String publicKey =  keyPairMap.getString("publicKey");
            System.out.println("私钥 => " + privateKey + "\n");
            System.out.println("公钥 =>" + publicKey + "\n");

            // RSA加密
            //String data = "123456";
            String data = "1234561ssssssssssssssssssssssssssssssssssssssssss1111";
            String encryptData = publicEncrypt(data, publicKey);
            System.out.println("加密后内容 => " + encryptData + "\n");
            // RSA解密
            String decryptData = privateDecrypt(encryptData, privateKey);
            System.out.println("解密后内容 => " + decryptData + "\n");


            String encryptData2 = privateEncrypt(data, privateKey);
            System.out.println("加密后内容 => " + encryptData2 + "\n");
            // RSA解密
            String decryptData2 = publicDecrypt(encryptData2, publicKey);
            System.out.println("解密后内容 => " + decryptData2 + "\n");


            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.println("验签结果 => " + result + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("RSA加解密异常");
        }
    }
}
