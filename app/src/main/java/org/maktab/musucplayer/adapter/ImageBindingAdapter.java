package org.maktab.musucplayer.adapter;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class ImageBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, Uri url) {
        if (!url.equals("")) {
            Picasso.get().load(url).into(view);
        }
    }
}
