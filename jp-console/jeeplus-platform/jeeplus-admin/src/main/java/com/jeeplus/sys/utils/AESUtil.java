package com.jeeplus.sys.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.jeeplus.security.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class AESUtil {
    static String  token = "a0a81285df754285";
    /**
     * AES加密 根据登录的token进行数据加密
     * @return
     */
    public static String encryptTokenHex(String data){

        String encryptHex = "";
        byte[] key = token.getBytes();
        if(StringUtils.isNotBlank(token)){
            //构建
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
            //加密为16进制表示
            encryptHex = aes.encryptHex(data);
        }

        return encryptHex;
    }

    /**
     * AES解密 根据登录的token进行数据解密
     * @return
     */
    public static String decryptTokenStr(String data){
//        Security.addProvider(new BouncyCastleProvider());//初始化bs提供者
        String decryptStr = "";
        byte[] key = token.getBytes();
        if(StringUtils.isNotBlank(token)){
            //构建
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
            //解密为字符串
            decryptStr = aes.decryptStr(data, CharsetUtil.CHARSET_UTF_8);
        }
        return decryptStr;
    }

    /**
     * AES加密
     * @return
     */
    public static String encryptHex(String data,String keys){
        String token = keys;
        String encryptHex = "";
        byte[] key = token.getBytes();
        if(StringUtils.isNotBlank(token)){
            //构建
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
            //加密为16进制表示
            encryptHex = aes.encryptHex(data);
        }

        return encryptHex;
    }

    /**
     * AES解密
     * @return
     */
    public static String decryptStr(String data,String keys){
        String token = SecurityUtils.getToken();
        String decryptStr = "";
        byte[] key = token.getBytes();
        if(StringUtils.isNotBlank(token)){
            //构建
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
            //解密为字符串
            decryptStr = aes.decryptStr(data, CharsetUtil.CHARSET_UTF_8);
        }
        return decryptStr;
    }




}
