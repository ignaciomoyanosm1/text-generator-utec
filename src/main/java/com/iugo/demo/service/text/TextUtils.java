package com.iugo.demo.service.text;

public final class TextUtils {
    private TextUtils() {}

    public static String clean(String s) {
        return s == null ? "" : s.replace("\r", "").trim();
    }

    public static String truncate(String s, int max) {
        if (s == null) return "";
        if (max <= 0) return "";
        return s.length() <= max ? s : s.substring(0, Math.max(0, max - 1)) + "…";
    }
}
