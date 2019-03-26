package com.xiaoliu.common.tools;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * @Description :NumberUtil工具类
 * @Author xiaoli.cheng
 * @Date 11:47 2018/12/19
 */
public final class NumberUtil {
    private final static Logger logger = Logger.getLogger(NumberUtil.class);

    private NumberUtil() {
    }

    /**
     * 获取length位随机数字字母组合
     * @param length
     * @return
     */
    public static String getRandomCharacterAndNumber(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 字符串
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // int choice = 97; // 指定字符串为小写字母
                builder.append((char) (choice + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                builder.append(String.valueOf(random.nextInt(10)));
            }
        }
        return builder.toString();
    }

}
