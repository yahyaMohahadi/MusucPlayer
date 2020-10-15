package org.maktab.musucplayer.view_model;

import android.net.Uri;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.utils.ListUtils;

public class AlbumViewModel extends ViewModel {

    public static final int LIMIT_ALBUM_STR = 9;
    private final ListUtils.Callbacks mCallbacks;
    private Album mAlbum;
    private Uri mUriImage;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public AlbumViewModel(Album album, ListUtils.Callbacks callbacks) {
        this.mCallbacks = callbacks;
        this.mAlbum = album;
        mUriImage = album.getSongAlbum().get(0).getUriImage();
        imageUrlUpdated(mUriImage);
    }

    public void onClick() {
        mCallbacks.itemCalled(ListUtils.States.ALBUMS, mAlbum.getStringAlbumName());
    }

    public String getTittle() {
        return ListUtils.limitString(mAlbum.getStringAlbumName(), LIMIT_ALBUM_STR);
    }

    public String getArtistName() {
        return ListUtils.limitString(mAlbum.getStringArtistName(), LIMIT_ALBUM_STR);
    }

    public String getMusicNumber() {
        return String.valueOf(mAlbum.getSongAlbum().size());
    }

    public void imageUrlUpdated(Uri uri) {
        resultImageUrl.set(uri);
    }

}
