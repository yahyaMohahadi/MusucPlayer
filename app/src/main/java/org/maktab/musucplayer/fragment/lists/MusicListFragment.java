package org.maktab.musucplayer.fragment.lists;

import android.os.Bundle;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;

public class MusicListFragment extends ListFragment {
    private Callbacks mCallbacks;

    public static MusicListFragment newInstance(Callbacks callbacks) {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacks = callbacks;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        return MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                ListFragment.States.MUSICS
                , mCallbacks
        );
    }

    @Override
    public int getFragmtView() {
        return R.layout.fragment_music_list;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerview_songs;
    }

}