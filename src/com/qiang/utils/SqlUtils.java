package com.qiang.utils;

import org.apache.commons.lang3.StringUtils;

public class SqlUtils {

    public static String format(String sql) {
        return StringUtils.isBlank(sql) ? null : sql.replaceAll("\\n", " ").replaceAll("\\t", " ").replaceAll("<br/>", " ").trim();
    }
}