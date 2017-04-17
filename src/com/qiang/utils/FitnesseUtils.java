package com.qiang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FitnesseUtils {


    public void sleep(int i) throws InterruptedException {
        Thread.sleep(i * 1000);
    }

    public long currTime() {
        return System.currentTimeMillis();
    }

    public String time() {
        return time("yyyy-MM-dd HH:mm:ss");
    }

    public String time(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public String date() {
        return date(0, "yyyy-MM-dd");
    }

    public String date(String format) {
        return date(0, format);
    }

    public String date(int n) {
        return date(n, "yyyy-MM-dd");
    }

    public String date(int n, String format) {
        return new SimpleDateFormat(format).format(n == 0 ? new Date() : new Date(new Date().getTime() + n * 3600L * 24 * 1000));
    }

    public String regex(String regex, String content) {
        return RegexUtils.regex(regex, content, 0);
    }

    public String regex(String regex, String content, int i) {
        return RegexUtils.regex(regex, content, i);
    }
   
}
