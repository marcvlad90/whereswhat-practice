package com.tools.utils;

import net.serenitybdd.core.Serenity;

import java.util.ArrayList;
import java.util.List;

public class SerenitySessionUtils {

    @SuppressWarnings("unchecked")
    public static <T> T getFromSession(String key) {
        return (T)Serenity.getCurrentSession().get(key);
    }

    public static void putOnSession(String key, Object object) {
        Serenity.getCurrentSession().put(key, object);
    }

    public static void removeFromSession(String key) {
        Serenity.getCurrentSession().remove(key);
    }

    @SuppressWarnings("unchecked")
    public static void saveObjectInTheListInSerenitySession(String key, Object obj) {
        if (!Serenity.getCurrentSession().containsKey(key)) {
            Serenity.getCurrentSession().put(key, new ArrayList<>());
        }
        ((List<Object>)Serenity.getCurrentSession().get(key)).add(obj);
    }
}
