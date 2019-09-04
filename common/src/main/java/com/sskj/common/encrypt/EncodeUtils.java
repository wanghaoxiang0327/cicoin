package com.sskj.common.encrypt;


public class EncodeUtils {
    public static String encodeAES(String content) {
        while (content.length() % 16 != 0) {
            content = content + " ";
        }
        return EncryptTool.encryptAES(content, "etf@test+aes4587").replace("+", "%2B");
    }

    public static String decryptAES(String content) {
        return EncryptTool.decryptAES(content, "etf@test+aes4587");
    }

    public static String encodeAES2B(String content) {
        while (content.length() % 16 != 0) {
            content = content + " ";
        }
        return EncryptTool.encryptAES(content, "bi_ecology_qwert").replace("+","%2B");
    }
}
