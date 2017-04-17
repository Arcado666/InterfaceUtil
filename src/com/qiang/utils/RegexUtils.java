package com.qiang.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtils {
    public static String regex(String regex, String content, int i) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        if (list.size() >= i)
            return list.get(i);
        return null;
    }

    public static void main(String args[]) {
        System.out.print(RegexUtils.regex("=(.*)", "P33326:=4****************8", 0));
    }
}
