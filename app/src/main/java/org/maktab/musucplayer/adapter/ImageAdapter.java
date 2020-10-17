package org.maktab.musucplayer.adapter;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class ImageAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, Uri uri) {
        if (view == null || uri == null) {
            return;
        }
        Glide.with(view.getContext())
                .load(uri)
                .centerCrop()
                .apply(new RequestOptions().transform(
                        new CenterCrop(), new RoundedCorners(12)
                ))
                .into(view);
    }
}