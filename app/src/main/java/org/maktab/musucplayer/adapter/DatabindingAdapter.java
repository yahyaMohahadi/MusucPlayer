package org.maktab.musucplayer.adapter;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.generated.callback.OnProgressChanged;

public class DatabindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, Uri uri) {
        if (view == null || uri == null)
            return;

        Glide.with(view.getContext())
                .load(uri)
                .placeholder(R.drawable.ic_empty_pic)
                .centerCrop()
                .apply(new RequestOptions().transform(
                        new CenterCrop(), new RoundedCorners(12)
                ))
                .into(view);
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI(null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:bufferType")
    public static void loadText(TextView view, String string) {
        if (view == null || string == null)
            return;
        view.setText(string);
    }


    //@BindingAdapter(value = {"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged", "android:progressAttrChanged"}

}