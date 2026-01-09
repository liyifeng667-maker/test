package com.jeeplus.security.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class CharacterUtils {

    /**
     * 替换银行入参特殊字符
     */
    public static String replaceBankParameter(String str){
        String regEx = "[‘&;\\[\\]<>|]";
        str = Pattern.compile(regEx).matcher(str).replaceAll("").trim();
        return str;
    }
}