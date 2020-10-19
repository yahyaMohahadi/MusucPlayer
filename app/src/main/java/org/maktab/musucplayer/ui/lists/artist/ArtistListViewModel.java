package org.maktab.musucplayer.ui.lists.artist;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicArtistAdapter;
import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.data.model.Artist;
import org.maktab.musucplayer.data.model.Song;

import java.util.ArrayList;
import java.util.List;

public class ArtistListViewModel extends ViewModel {
    private MusicArtistAdapter mAdapter;
    private MutableLiveData<List<Artist>> mListMutableLiveData = new MutableLiveData<>();

    public ArtistListViewModel() {
    }

    public MutableLiveData<List<Artist>> getListMutableLiveData() {
        return mListMutableLiveData;
    }

    public void setupRecyclerView(RecyclerView recyclerviewArtist) {
        if (mAdapter == null) {
            recyclerviewArtist.setLayoutManager(new LinearLayoutManager(recyclerviewArtist.getContext()));
            mAdapter = MusicArtistAdapter.newInstance(mListMutableLiveData.getValue());
            recyclerviewArtist.setAdapter(mAdapter);
        } else {
            mAdapter.setSongs(mListMutableLiveData.getValue());
            mAdapter.notifyDataSetChanged();
        }
    }

    public void fetchLiveData(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Song> songs = new ArrayList<>();
                songs.addAll(SongRepository.newInstance(context).getSongs());
                mListMutableLiveData.postValue(
                        SongRepository.getArtistsFromSongs(songs)
                );
            }
        }).start();

    }
}
