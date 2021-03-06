package com.xiaoliu.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description: NumberUtil工具类
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
public final class JsonUtil {

    private JsonUtil() {
    }

    /**
     * bean对象转json对象
     *
     * @param cls
     * @param obj
     * @return
     */
    public static JSONObject getJSON(Class<?> cls, Object obj) {
        JSONObject json = new JSONObject();
        Field[] fieldArray = cls.getDeclaredFields();
        for (Field field : fieldArray) {
            String fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) {
                continue;
            }
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method method = cls.getMethod(methodName);
                Object fieldObject = method.invoke(obj, new Object[0]);
                String fieldValue = null;
                if (fieldObject != null) {
                    fieldValue = fieldObject.toString();
                }
                json.put(fieldName, fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    /**
     * json转Map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject json) {
        return JSONObject.parseObject(json.toJSONString(), new TypeReference<TreeMap<String, Object>>() {
        });
    }

    public static Map<String, Object> jsonToMap(String strJson) {
        return JSONObject.parseObject(strJson, new TypeReference<TreeMap<String, Object>>() {
        });
    }

    /**
     * 获取json中指定key的值
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static String getString(JSONObject jsonObject, String key) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(key)) ? jsonObject.getString(key) : "";
    }

}
