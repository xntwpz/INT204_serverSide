package dev.bestzige.simplerbacjavaservlet.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
