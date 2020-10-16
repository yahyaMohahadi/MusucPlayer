package org.maktab.musucplayer.data.model;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Song implements Serializable {

    private String mStringTitle;
    private String mStringArtist;
    private String mStringAlbum;
    private int mIntId;
    private Uri mUri;
    private Long mLongAlbumId;


    public static class Bilder {
        private String mStringTitle;
        private String mStringArtist;
        private String mStringAlbum;
        private int mIntId;
        private Uri mUri;
        private Long mLongAlbumId;

        public Bilder() {
        }

        public Bilder setLongAlbumId(Long longAlbumId) {
            mLongAlbumId = longAlbumId;
            return this;
        }

        public Bilder setStringTitle(String stringTitle) {
            mStringTitle = stringTitle;
            return this;
        }

        public Bilder setUri(Uri uri) {
            mUri = uri;
            return this;
        }

        public Bilder setStringArtist(String stringArtist) {
            mStringArtist = stringArtist;
            return this;
        }

        public Bilder setStringAlbum(String stringAlbum) {
            mStringAlbum = stringAlbum;
            return this;
        }

        public Bilder setIntId(int intId) {
            mIntId = intId;
            return this;
        }

        public Song creat() {
            Song song = new Song();
            song.mIntId = this.mIntId;
            song.mStringAlbum = this.mStringAlbum;
            song.mStringArtist = this.mStringArtist;
            song.mStringTitle = this.mStringTitle;
            song.mUri = this.mUri;
            song.mLongAlbumId = this.mLongAlbumId;
            return song;
        }
    }

    public String getStringTitle() {
        return mStringTitle;
    }

    public String getStringArtist() {
        return mStringArtist;
    }

    public String getStringAlbum() {
        return mStringAlbum;
    }

    public Long getLongAlbumId() {
        return mLongAlbumId;
    }

    public int getIntId() {
        return mIntId;
    }

    public Uri getUri() {
        return mUri;
    }

    public  Bitmap getImageSong( Context context) {
        Bitmap bm = null;
        ParcelFileDescriptor pfd = null;
        Uri uri = getUriImage();
        try {
            pfd = context.getContentResolver()
                    .openFileDescriptor(uri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (pfd != null) {
            FileDescriptor fd = pfd.getFileDescriptor();
            bm = BitmapFactory.decodeFileDescriptor(fd);
        }
        return bm;
    }

    public Uri getUriImage() {
        final Uri sArtworkUri = Uri
                .parse("content://media/external/audio/albumart");

        return ContentUris.withAppendedId(sArtworkUri, mLongAlbumId);
    }

    public  Bitmap getImageMusicSize(Context context){
        return  Bitmap.createScaledBitmap(getImageSong(context), 40, 40, true);
    }
}
