package com.zwc.utils;

import com.zwc.pojo.User;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author: zhangwch
 * @create: 2020-05-18 17:15
 **/
public class Util {

    // 通过反射获取字段名
    public static String getFieldType(Class<?> object) throws Exception {
        Field[] fields = object.getDeclaredFields();
//        System.out.println("args = [" + Arrays.toString(fields) + "]");

//        System.out.println("object = [" + fields.length + "]");
        StringBuilder fieldTypeStr = new StringBuilder("Columns: ");
        for (Field field : fields) {
            field.setAccessible(true);
            fieldTypeStr.append(field.getName().toUpperCase() + " ");
        }
        return fieldTypeStr.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("args = [" + getFieldType(User.class) + "]");
    }
}
