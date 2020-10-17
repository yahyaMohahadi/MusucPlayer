package org.maktab.musucplayer.utils;

public class StringLimiting {

    public static final int LIMIT_CHARE_AETIST = 9;
    public static final int LIMIT_CHARE_TITTLE = 45;

    public static String limitString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }
}
