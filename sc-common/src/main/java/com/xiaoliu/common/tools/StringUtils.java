package com.xiaoliu.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 字符串工具类
 * @author: FuBiaoLiu
 * @date: 2019/9/4
 */
public class StringUtils {
    /**
     * 把字符串中的空格、回车、换行符、制表符等都换成空格
     */
    private static Pattern p1 = Pattern.compile("\\t|\r|\n");
    private static Pattern p2 = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|/\\*.*?\\*/|#.*?$|");

    /**
     * 去除sql当中的注释
     *
     * @param sql
     * @return
     */
    public static String replaceCommonsOfSql(String sql) {
        String repl = "";
        if (sql != null) {
            repl = p2.matcher(sql).replaceAll("$1");
        }
        return repl;
    }

    /**
     * sql语句格式化，去掉特殊字符和空格
     * <p>
     * 把字符串中的空格、回车、换行符、制表符等都换成空格，
     * 然后把多个空格换成一个空格
     *
     * @param str
     * @return
     */
    public static String replaceSpecialStrToBlank(String str) {
        String repl = "";
        if (str != null) {
            Matcher m = p1.matcher(str);
            repl = m.replaceAll(" ");
        }
        repl = repl.replace("\"\"", "");
        // 然后把多个空格换成一个空格
        repl = repl.replaceAll(" +", " ");
        return repl;
    }

    public static boolean isBlank(String str) {
        return org.apache.commons.lang.StringUtils.isBlank(str) || "null".equalsIgnoreCase(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 统一字符串的长度
     *
     * @param source  源字符串
     * @param length  统一的长度
     * @param fillStr 填充的字符串
     * @return 统一长度的字符串
     */
    public static String sameLength(String source, int length, String fillStr) {
        int diff = length - source.length();
        StringBuilder strs = new StringBuilder();
        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                strs.append(fillStr);
            }
        }
        return strs.append(source).toString();
    }


    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return (str == null) || (str.isEmpty());
    }

    public static boolean isNotEmpty(String str) {
        return (str != null) && (!str.isEmpty());
    }

    public static boolean isTrimBlank(String str) {
        return (str == null) || (str.trim().isEmpty());
    }

    public static boolean isNoTrimBlank(String str) {
        return (str != null) && (!str.trim().isEmpty());
    }

    public static String capUpperCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    public static String capLowerCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase().concat(str.substring(1));
    }

    public static String getString(Object obj, String defaultStr) {
        if (obj != null) {
            return obj.toString();
        }
        return defaultStr;
    }

    public static String getString(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }
}
