package org.maktab.musucplayer.ui.lists.artist;

import android.net.Uri;

import androidx.databinding.ObservableField;

import org.maktab.musucplayer.adapter.MusicArtistAdapter;
import org.maktab.musucplayer.data.model.Artist;
import org.maktab.musucplayer.ui.Callback;

import java.util.Observable;

public class ArtistViewModel extends Observable {
    private Artist mArtist;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();
    private MusicArtistAdapter.CallbackOpenable mCallbackOpenable;

    public ArtistViewModel(Artist artist) {
        mArtist = artist;
        imageUrlUpdated();
    }

    public void onClick() {
        if (mCallbackOpenable != null)
            mCallbackOpenable.onClick(mArtist);
    }

    public void imageUrlUpdated() {
        resultImageUrl.set(mArtist.getSongArtist().get(0).getUriImage());
    }


    public String getArtist() {
        return mArtist.getStringArtistName();
    }

    public String getNumber() {
        return String.valueOf(mArtist.getSongArtist().size());
    }

    public void setCallback(MusicArtistAdapter.CallbackOpenable callbackOpenable) {
        mCallbackOpenable = callbackOpenable;
    }
}
