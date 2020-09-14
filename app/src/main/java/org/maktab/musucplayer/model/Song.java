package org.maktab.musucplayer.model;

import java.io.Serializable;

public class Song implements Serializable {

    private String mStringTitle;
    private String mStringArtist;
    private String mStringAlbum;
    private int mIntId;


    public static class Bilder {
        private String mStringTitle;
        private String mStringArtist;
        private String mStringAlbum;
        private int mIntId;

        public Bilder() {
        }

        public Bilder setStringTitle(String stringTitle) {
            mStringTitle = stringTitle;
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
}
