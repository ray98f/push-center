package com.zte.msg.pushcenter.pccore.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zte.msg.pushcenter.pccore.dto.SignView;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectUtils {

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取属性名数组
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private static List<Map<String, Object>> getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> infoMap = null;
        for (Field field : fields) {
            infoMap = new HashMap<>();
            infoMap.put("type", field.getType().toString());
            infoMap.put("name", field.getName());
            infoMap.put("value", getFieldValueByName(field.getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     */
    public static String getFiledValues(Object o) {
        String[] fieldNames = getFiledName(o);
        StringBuilder stringBuilder = new StringBuilder();
        for (String fieldName : fieldNames) {
            stringBuilder.append(getFieldValueByName(fieldName, o));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        SignView signView = new SignView(new SmsMessageReqDTO(), TokenUtil.getTimestamp(),"");
//        System.out.println(signView.toString());
    }
}