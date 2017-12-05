package com.match.admin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/4 0004.
 */
public class Misc {
    public static String getStrByPattern(String patternStr, String string) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(string);
        String targetStr = null;
        if (matcher.find()) {
            targetStr = matcher.group();
        }
        return targetStr;
    }
}
