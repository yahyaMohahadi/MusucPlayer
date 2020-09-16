package org.maktab.musucplayer.model;

import android.net.Uri;

import java.io.Serializable;

public class Song implements Serializable {

    private String mStringTitle;
    private String mStringArtist;
    private String mStringAlbum;
    private int mIntId;
    private Uri mUri;


    public static class Bilder {
        private String mStringTitle;
        private String mStringArtist;
        private String mStringAlbum;
        private int mIntId;
        private Uri mUri;

        public Bilder() {
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

    public int getIntId() {
        return mIntId;
    }

    public Uri getUri() {
        return mUri;
    }
}
