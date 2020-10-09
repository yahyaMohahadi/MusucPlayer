package org.maktab.musucplayer.view_model;

import android.net.Uri;
import android.util.Log;

import androidx.databinding.ObservableField;

import org.maktab.musucplayer.model.Album;

public class AlbumViewModel {

    private Album mAlbum;
    private Uri mUriImage;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public AlbumViewModel(Album album) {
        this.mAlbum = album;
        mUriImage = album.getSongAlbum().get(0).getUriImage();
        imageUrlUpdated(mUriImage);
    }

    public void onClick() {
        //todo logic
        Log.d("QQQ", mAlbum.getStringAlbumName());
    }

    public String getTittle() {
        return mAlbum.getStringAlbumName();
    }

    public String getArtistName() {
        return mAlbum.getStringArtistName();
    }

    public String getMusicNumber() {
        return String.valueOf(mAlbum.getSongAlbum().size());
    }

    public void imageUrlUpdated(Uri uri) {
        resultImageUrl.set(uri);
    }

}
