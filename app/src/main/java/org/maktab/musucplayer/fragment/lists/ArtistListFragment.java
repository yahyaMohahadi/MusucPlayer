package org.maktab.musucplayer.fragment.lists;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.Music;


public class ArtistListFragment extends ListFragment {

    private Callbacks mCallbacksl;
    private MusicListAdapter mMusicListAdapter;

    public static ArtistListFragment newInstance(Callbacks callbacks) {
        ArtistListFragment fragment = new ArtistListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacksl = callbacks;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    void updateList() {
        try {
            mMusicListAdapter.setSongs(Music.newInstance().getSongList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
    }


    @Override
    public MusicListAdapter getMusicAdapter() {
        mMusicListAdapter = MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                States.ARTISTS,
                mCallbacksl
        );
        return mMusicListAdapter;
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