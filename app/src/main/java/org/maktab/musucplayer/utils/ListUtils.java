package org.maktab.musucplayer.utils;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class ListUtils {
    public static String limitString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }

    public static class ImageBindingAdapter {

        @BindingAdapter("bind:imageUrl")
        public static void loadImage(ImageView view, Uri url) {
            if (url != null) {
                if (!url.equals(""))
                    Picasso.get().load(url).into(view);
            }
        }
    }
}
