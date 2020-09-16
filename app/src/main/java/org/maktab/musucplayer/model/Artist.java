package org.maktab.musucplayer.model;

import java.util.ArrayList;

public class Artist {
    private String mStringArtistName;
    private ArrayList<Song> mSongArtist = new ArrayList<>();

    public String getStringArtistName() {
        return mStringArtistName;
    }

    public void setStringArtistName(String stringArtistName) {
        mStringArtistName = stringArtistName;
    }

    public ArrayList<Song> getSongArtist() {
        return mSongArtist;
    }

    public void setSongArtist(ArrayList<Song> songArtist) {
        mSongArtist = songArtist;
    }
}
