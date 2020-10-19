package org.maktab.musucplayer.ui.lists.artist;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import org.maktab.musucplayer.data.model.Artist;

import java.util.Observable;

public class ArtistViewModel extends Observable {
    private Artist mArtist;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();
    public ArtistViewModel(Artist artist) {
        mArtist = artist;
        imageUrlUpdated();
    }

    public void onClick() {

    }
    public void imageUrlUpdated() {
        resultImageUrl.set(mArtist.getSongArtist().get(0).getUriImage());
    }


    public String getArtist() {
        return mArtist.getStringArtistName();
    }

    public String getNumber(){
        return String.valueOf(mArtist.getSongArtist().size());
    }
}
