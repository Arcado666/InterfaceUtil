package com.qiang.utils;

import java.util.*;

public class AppEncrypt {
    public static String paramsMd5(Map<String, Object> pars, long systemTimes, String businessStr) {
        List<Map.Entry<String, Object>> params = new ArrayList<Map.Entry<String, Object>>(pars.entrySet());//
        Collections.sort(params, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                if (o1.getKey() == null || o2.getKey() == null)
                    return 0;
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String secret = "";
        for (Map.Entry<String, Object> param : params) {
            String key = param.getKey();
            Object value = param.getValue();
            secret = secret + key + "=" + value + "&";
        }
        if (secret.endsWith("&"))
            secret = secret.substring(0, secret.length() - 1);
        System.out.println("secret======" + secret);

        long currentTime = systemTimes / 100000;

        String timestamp = String.valueOf(currentTime);
        String md5 = Encrypt.decryptKey(secret.getBytes(), timestamp.getBytes(), businessStr.getBytes());
        System.out.println("md5=====" + md5);
        return md5;
        //httppost.addHeader("App-Secret", md5);
    }
}
