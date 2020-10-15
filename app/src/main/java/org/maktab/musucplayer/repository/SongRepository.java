package org.maktab.musucplayer.repository;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.view_model.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    private List<Song> mSongs;

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @SuppressLint("StaticFieldLeak")
    private static SongRepository sRepository;

    private SongRepository() {
    }

    public static SongRepository newInstance(Context context) {
        if (sRepository == null) {
            sRepository = new SongRepository();
            sContext = context.getApplicationContext();
        }
        if (sRepository.mSongs == null) {
            try {
                sRepository.mSongs = getMdediFromContentResolver(sContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sRepository;
    }

    public boolean updateSongs() {
        try {
            sRepository.mSongs = getMdediFromContentResolver(sContext);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public List<Song> getSongAlbum(String album) {
        List<Song> songAlbum = new ArrayList<>();
        for (Song song : mSongs) {
            if (song.getStringAlbum().equals(album)) {
                songAlbum.add(song);
            }
        }
        return songAlbum;
    }

    public List<Song> getSongArtist(String artist) {
        List<Song> songArtist = new ArrayList<>();
        for (Song song : mSongs) {
            if (song.getStringArtist().equals(artist)) {
                songArtist.add(song);
            }
        }
        return songArtist;
    }

    public static ArrayList<Artist> getArtistsFromSongs(ArrayList<Song> songs) {
        ArrayList<Artist> artists = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            boolean artistExist = false;
            for (int j = 0; j < artists.size(); j++) {
                if (artists.get(j).getStringArtistName().equals(songs.get(i).getStringArtist())) {
                    artists.get(j).getSongArtist().add(songs.get(i));
                    artistExist = true;
                    break;
                }
            }
            if (!artistExist) {
                Artist artist = new Artist();
                artist.setStringArtistName(songs.get(i).getStringArtist());
                artist.getSongArtist().add(songs.get(i));
                artists.add(artist);
            }
        }
        return artists;
    }

    public static ArrayList<Album> getAlbumFromSongs(ArrayList<Song> songs) {
        ArrayList<Album> albums = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            boolean albumistExist = false;
            for (int j = 0; j < albums.size(); j++) {
                if (albums.get(j).getStringAlbumName().equals(songs.get(i).getStringAlbum())) {
                    albums.get(j).getSongAlbum().add(songs.get(i));
                    albumistExist = true;
                }
            }
            if (!albumistExist) {
                Album album = new Album();
                album.setStringAlbumName(songs.get(i).getStringAlbum());
                album.getSongAlbum().add(songs.get(i));
                albums.add(album);
            }
        }
        return albums;
    }

    public List<Song> getListSong(SongRepository.StateAskSong states, String stringSelected) {
        //todo manage state song asked
     /*   List<Song> songs = null;
        switch (states) {
            case MUSIC_ALBUM: {
                songs = this.getSongAlbum(stringSelected);
                break;
            }
            case MUSIC_ARTIST: {
                songs = this.getSongArtist(stringSelected);
                break;
            }
            case DITAIL:{
                break;
            }
            case MAIN: {
                songs = mSongs;
                break;
            }
        }*/
        return mSongs;
    }

    public Song getSongsById(int id) {
        for (Song song : mSongs) {
            if (song.getIntId() == id) {
                return song;
            }
        }
        return null;
    }


    private static List<Song> getMdediFromContentResolver(Context context) throws Exception {
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
        } finally {
            if (musicCursor != null) {
                musicCursor.close();
            }
        }
        return songs;

    }

    /**
     * get key for courser ==> https://developer.android.com/reference/android/provider/MediaStore.Audio.AudioColumns
     *
     * @param musicCursor that can get from media
     * @return musicList will be return
     */
    private static Song getSongFromCursor(Cursor musicCursor) {
        String title = musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media.TITLE));
        int id = Integer.parseInt(musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media._ID)));
        String artist = musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media.ARTIST));
        String albume = musicCursor.getString(musicCursor.getColumnIndex
                (MediaStore.Audio.Media.ALBUM));
        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

        long albumId =
                Long.parseLong(musicCursor.getString(
                        musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));

        return new Song.Bilder()
                .setIntId(id)
                .setStringAlbum(albume)
                .setStringTitle(title)
                .setStringArtist(artist)
                .setUri(contentUri)
                .setLongAlbumId(albumId)
                .creat();
    }

    public enum StateAskSong {
        ONE_MUSIC,ALL_MUSIC,ALBUM_MUSIC,ARTIST_MUSIC
    }
}

