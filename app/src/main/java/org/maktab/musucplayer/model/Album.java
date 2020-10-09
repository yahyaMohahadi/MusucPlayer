package org.maktab.musucplayer.model;

import java.util.ArrayList;

public class Album {
    private String mStringAlbumName;
    private String mStringArtistName;
    private ArrayList<Song> mSongAlbum = new ArrayList<>();

    public String getStringAlbumName() {
        return mStringAlbumName;
    }

    public void setStringAlbumName(String stringAlbumName) {
        mStringAlbumName = stringAlbumName;
    }

    public ArrayList<Song> getSongAlbum() {
        return mSongAlbum;
    }

    public String getStringArtistName() {
        return mSongAlbum.get(0).getStringArtist();
    }

    public void setSongAlbum(ArrayList<Song> songAlbum) {
        mSongAlbum = songAlbum;
    }
}
