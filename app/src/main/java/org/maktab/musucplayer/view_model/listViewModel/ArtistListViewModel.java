package org.maktab.musucplayer.view_model.listViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicArtistAdapter;
import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.view_model.MusicViewModel;

import java.util.ArrayList;
import java.util.List;

public class ArtistListViewModel extends ViewModel {
    private Context mContext;
    private List<Artist> mArtists;

    public ArtistListViewModel(Context context) {
        mContext = context.getApplicationContext();
        List<Song> all = MusicViewModel.newInstance().getMutableLiveDataAllSongs().getValue();
        mArtists = SongRepository.getArtistsFromSongs((ArrayList<Song>) all);

    }

    public void setupRecyclerView(RecyclerView recyclerviewAlbum) {
        recyclerviewAlbum.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        MusicArtistAdapter adapter = MusicArtistAdapter.newInstance(mArtists);
        recyclerviewAlbum.setAdapter(adapter);
    }


}
