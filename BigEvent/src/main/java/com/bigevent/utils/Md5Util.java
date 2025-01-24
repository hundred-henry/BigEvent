package com.bigevent.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * 16 进制字符数组，用于将字节转换成 16 进制表示。
     */
    protected static final char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * MD5 消息摘要对象。
     */
    protected static MessageDigest messageDigest = null;

    // 静态初始化块，初始化 MessageDigest 实例
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName() + " 初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 生成字符串的 MD5 哈希值。
     *
     * @param s 要生成哈希值的字符串
     * @return 字符串的 MD5 哈希值（32 位小写十六进制）
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 生成字节数组的 MD5 哈希值。
     *
     * @param bytes 要生成哈希值的字节数组
     * @return 字节数组的 MD5 哈希值（32 位小写十六进制）
     */
    public static String getMD5String(byte[] bytes) {
        messageDigest.update(bytes);
        return bufferToHex(messageDigest.digest());
    }

    /**
     * 校验输入的字符串与 MD5 哈希值是否匹配。
     *
     * @param password 原始字符串
     * @param md5Str   要校验的 MD5 哈希值
     * @return 是否匹配
     */
    public static boolean checkPassword(String password, String md5Str) {
        String calculatedMd5 = getMD5String(password);
        return calculatedMd5.equals(md5Str);
    }

    /**
     * 将字节数组转换为十六进制字符串。
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bufferToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            stringBuilder.append(hexDigits[(b >> 4) & 0xF]); // 高 4 位
            stringBuilder.append(hexDigits[b & 0xF]);       // 低 4 位
        }
        return stringBuilder.toString();
    }
}
