package org.maktab.musucplayer.utils;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


public class ListUtils {
    public static String limitString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }
}
