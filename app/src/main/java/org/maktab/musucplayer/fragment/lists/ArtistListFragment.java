package org.maktab.musucplayer.fragment.lists;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.repository.SongRepository;


public class ArtistListFragment extends ListFragment {

    private Callbacks mCallbacksl;

    public static ArtistListFragment newInstance(Callbacks callbacks) {
        ArtistListFragment fragment = new ArtistListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacksl = callbacks;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
    }


    @Override
    public MusicListAdapter getMusicAdapter() {
        return MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                States.ARTISTS,
                mCallbacksl
        );
    }

    @Override
    public int getFragmtView() {
        return R.layout.fragment_artist_list;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerview_artist;
    }


}