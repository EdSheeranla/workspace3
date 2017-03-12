package com.hjy.shop.util;

import java.util.UUID;

/**
 * Created by admin on 2017/3/8.
 * 生成UUID
 */
public class UUIDutil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
