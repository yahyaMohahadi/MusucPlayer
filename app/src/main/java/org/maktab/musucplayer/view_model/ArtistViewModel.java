package org.maktab.musucplayer.view_model;

import android.net.Uri;
import android.util.Log;

import androidx.databinding.ObservableField;

import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.utils.ListUtils;

public class ArtistViewModel {

    public static final int LIMIT_ARTIST_STR = 9;
    private final ListUtils.Callbacks mCallbacks;
    private Artist mAlbum;
    private Uri mUriImage;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public ArtistViewModel(Artist artist, ListUtils.Callbacks callbacks) {
        this.mCallbacks=callbacks;
        this.mAlbum = artist;
        //todo download image
        mUriImage = artist.getSongArtist().get(0).getUriImage();
        imageUrlUpdated(mUriImage);
    }

    public void onClick() {
        mCallbacks.itemCalled(ListUtils.States.ARTISTS,mAlbum.getStringArtistName());
    }


    public String getArtistName() {
        return ListUtils.limitString(mAlbum.getStringArtistName(), LIMIT_ARTIST_STR);

    }

    public String getMusicNumber() {
        return String.valueOf(mAlbum.getSongArtist().size());
    }

    public void imageUrlUpdated(Uri uri) {
        resultImageUrl.set(uri);
    }

}
