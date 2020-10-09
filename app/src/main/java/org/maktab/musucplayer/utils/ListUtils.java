package org.maktab.musucplayer.utils;

public class ListUtils {
    public interface Callbacks {
        void itemCalled(ListUtils.States states, String item);
    }

    public enum States {
        ALBUMS, ARTISTS, MUSICS, MUSIC_ARTIST, MUSIC_ALBUM
    }
}
