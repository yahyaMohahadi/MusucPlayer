package org.maktab.musucplayer.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import org.maktab.musucplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class Music {
    public static List<Song> getMdediFromContentResolver(Context context) throws Exception {
        List<Song> songs = new ArrayList<>();
        Cursor musicCursor = null;
        try {
            ContentResolver musicResolver = context.getContentResolver();
            Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            musicCursor = musicResolver.query(musicUri, null, null, null, null);
            if (musicCursor.moveToFirst() && musicCursor.getCount() > 0) {

                while (!musicCursor.isAfterLast()) {
                    Song song = getSongFromCursor(musicCursor);
                    songs.add(song);
                    musicCursor.moveToNext();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log.e("QQQ", e.getMessage());
        } finally {
            musicCursor.close();
        }
        return songs;

    }

    /**
     * get key for courser ==> https://developer.android.com/reference/android/provider/MediaStore.Audio.AudioColumns
     *
     * @param musicCursor
     * @return
     */
    public static Song getSongFromCursor(Cursor musicCursor) {
        String title = musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media.TITLE));
        int id = Integer.parseInt(musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media._ID)));
        String artist = musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media.ARTIST));
        String albume = musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media.ALBUM));
        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
        /*     for (String s : musicCursor.getColumnNames()) {
            Log.d("QQQ", s + " ---- " + musicCursor.getString(musicCursor.getColumnIndex(s)));
        }*/
        return new Song.Bilder()
                .setIntId(id)
                .setStringAlbum(albume)
                .setStringTitle(title)
                .setStringArtist(artist)
                .setUri(contentUri)
                .creat();
    }
}

