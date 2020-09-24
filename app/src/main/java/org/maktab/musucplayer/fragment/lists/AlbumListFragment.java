package org.maktab.musucplayer.fragment.lists;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;

public class AlbumListFragment extends ListFragment {
    private Callbacks mCallbacks;

    public static AlbumListFragment newInstance(Callbacks callbacks) {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacks = callbacks;
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
                ListFragment.States.ALBUMS,
                mCallbacks
        );

    }

    @Override
    public int getFragmtView() {
        return R.layout.fragment_album_list;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerview_album;
    }
}