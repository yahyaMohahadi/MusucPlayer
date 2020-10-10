package org.maktab.musucplayer.utils;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class ListUtils {
    public interface Callbacks {
        void itemCalled(ListUtils.States states, String item);
    }
    public static String limitString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }

    public enum States {
        ALBUMS, ARTISTS, MUSICS, MUSIC_ARTIST, MUSIC_ALBUM
    }

    public static class ImageBindingAdapter {

        @BindingAdapter("bind:imageUrl")
        public static void loadImage(ImageView view, Uri url) {
            if (!url.equals("")) {
                Picasso.get().load(url).into(view);
            }
        }
    }
}
