package org.maktab.musucplayer.ui.lists.album;

import android.net.Uri;

import androidx.databinding.ObservableField;

import org.maktab.musucplayer.data.model.Album;
import org.maktab.musucplayer.ui.Callback;

import java.util.Observable;

public class AlbumViewModel extends Observable {
    private Album mAlbum;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();
    private Callback<Album> mCallback;

    public AlbumViewModel(Album album) {
        mAlbum = album;
        imageUrlUpdated();
    }

    public void setCallback(Callback<Album> callback) {
        mCallback = callback;
    }

    public void onClick() {
        mCallback.onClick(mAlbum);
    }

    public void imageUrlUpdated() {
        resultImageUrl.set(mAlbum.getSongAlbum().get(0).getUriImage());
    }


    public String getAlbumName() {
        return mAlbum.getStringAlbumName();
    }

    public String getNumber() {
        return String.valueOf(mAlbum.getSongAlbum().size());
    }

    public String getAtristName() {
        return String.valueOf(mAlbum.getSongAlbum().get(0).getStringArtist());
    }
}
