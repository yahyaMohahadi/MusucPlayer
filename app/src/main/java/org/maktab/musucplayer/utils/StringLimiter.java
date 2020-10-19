package org.maktab.musucplayer.utils;

public class StringLimiter {

    public static final int LIMIT_CHARE_AETIST = 9;
    public static final int LIMIT_CHARE_TITTLE = 45;
    public static final int LIMIT_CHARE_BAR_TITTLE = 30;

    public static String limitString(String string, int limit) {
        if (string==null)
            return "";
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }
}
