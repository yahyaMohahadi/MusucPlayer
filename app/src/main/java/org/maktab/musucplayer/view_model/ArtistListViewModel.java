package org.maktab.musucplayer.view_model;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicAlbumAdapter;
import org.maktab.musucplayer.adapter.MusicArtistAdapter;
import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class ArtistListViewModel {
    private Context mContext;
    private List<Artist> mArtists;
    private ListUtils.Callbacks mCallbacks;

    public ArtistListViewModel(Context context,ListUtils.Callbacks callbacks) {
        mCallbacks = callbacks;
        mContext = context.getApplicationContext();
        mArtists = SongRepository.getArtistsFromSongs((ArrayList<Song>) SongRepository.newInstance(mContext).getSongs());

    }

    public void setupRecyclerView(RecyclerView recyclerviewAlbum) {
        recyclerviewAlbum.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        MusicArtistAdapter adapter = MusicArtistAdapter.newInstance(mArtists,mCallbacks);
        recyclerviewAlbum.setAdapter(adapter);
    }


}
