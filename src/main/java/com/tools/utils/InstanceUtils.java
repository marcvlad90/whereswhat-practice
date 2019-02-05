package com.tools.utils;

import java.lang.reflect.Field;

public class InstanceUtils {
    public static Object mergeObjects(Object obj1, Object obj2) {
        Field[] allFields = obj1.getClass().getDeclaredFields();
        for (Field field : allFields) {

            field.setAccessible(true);

            try {
                if (((field.get(obj1) == null) || field.get(obj1).equals(0) || field.get(obj1).equals(false)) && (field.get(obj2) != null)) {
                    field.set(obj1, field.get(obj2));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return obj1;
    }
}
