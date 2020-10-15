package org.maktab.musucplayer.view_model.itemViewModel;

import android.net.Uri;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.MainViewModel;

public class ArtistViewModel extends ViewModel {

    public static final int LIMIT_ARTIST_STR = 9;

    private Artist mAlbum;
    private Uri mUriImage;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public ArtistViewModel(Artist artist) {

        this.mAlbum = artist;
        //todo download image
        mUriImage = artist.getSongArtist().get(0).getUriImage();
        imageUrlUpdated(mUriImage);
    }

    public void onClick() {
        //todo manage click
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
