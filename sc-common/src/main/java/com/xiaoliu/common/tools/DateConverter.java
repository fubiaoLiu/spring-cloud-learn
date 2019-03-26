package com.xiaoliu.common.tools;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: Zeng
 * @date: 2018/4/23
 */
public class DateConverter implements Converter<String,Date> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public Date convert(String s) {
        if ("".equals(s) || s == null) {
            return null;
        }
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date converts(String s) {
        if ("".equals(s) || s == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            return simpleDateFormat2.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date convertFor(String s) {
        if ("".equals(s) || s == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormatFor = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormatFor.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date date) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormatFor = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormatFor.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
