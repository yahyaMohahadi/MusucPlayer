package org.maktab.musucplayer.fragment.lists;

import android.net.Uri;
import android.os.Bundle;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;

public class MusicListFragment extends ListFragment {

    public static MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        return MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                MusicListAdapter.State.MUSICS
                , new MusicListAdapter.Callbacks() {
                    @Override
                    public void onItemSelected(Uri musicUri) {

                    }
                }
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