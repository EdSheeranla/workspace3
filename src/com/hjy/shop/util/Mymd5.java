package com.hjy.shop.util;

import java.security.MessageDigest;

/**
 * @author EdSherran
 */
public class Mymd5 {

    static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String doMD5s(String oragindate) {
        String date = "";
        String salt = "huang";
        date = Mymd5.doMD5(Mymd5.doMD5(oragindate) + salt);
        return date;
    }

    public static String doMD5(String oragindate) {
        String date = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(oragindate.getBytes());
            date = byte2str(md5.digest());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return date;
    }

    /**
     * ���ֽ�����ת����ʮ�������ַ���
     *
     * @param bytes
     * @return
     */
    private static String byte2str(byte[] bytes) {
        int len = bytes.length;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; i++) {
            byte byte0 = bytes[i];
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }
} 
