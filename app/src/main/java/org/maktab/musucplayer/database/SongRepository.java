package org.maktab.musucplayer.database;

import android.content.Context;
import android.util.Log;

import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.utils.Music;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    private List<Song> mSongs;

    private static Context sContext;

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
                sRepository.mSongs = Music.getMdediFromContentResolver(sContext);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("QQQ", "erore for geting Music");
            }
        }
        return sRepository;
    }

    public boolean updateSongs() {
        try {
            sRepository.mSongs = Music.getMdediFromContentResolver(sContext);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("QQQ", "erore for updating Music");
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
            if (song.getStringAlbum().equals(artist)) {
                songArtist.add(song);
            }
        }
        return songArtist;
    }

    public static ArrayList<Artist> getArtistsFromSongs(ArrayList<Song> songs) {
        ArrayList<Artist> artists = new ArrayList<>();
        lopSong:
        for (int i = 0; i < songs.size(); i++) {
            boolean artistExist = false;
            lopArtist:
            for (int j = 0; j < artists.size(); j++) {
                if (artists.get(j).getStringArtistName().equals(songs.get(i).getStringArtist())) {
                    artists.get(j).getSongArtist().add(songs.get(i));
                    artistExist = true;
                    break lopArtist;
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
        lopSong:
        for (int i = 0; i < songs.size(); i++) {
            boolean albumistExist = false;
            loopAlbum:
            for (int j = 0; j < albums.size(); j++) {
                if (albums.get(j).getStringAlbumName().equals(songs.get(i).getStringAlbum())) {
                    albums.get(j).getSongAlbum().add(songs.get(i));
                    albumistExist = true;
                    break loopAlbum;
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

}
