package org.maktab.musucplayer.fragment.lists;

import android.os.Bundle;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;
import org.maktab.musucplayer.model.Song;

import java.util.List;

public class MusicListFragment extends ListFragment {
    private Callbacks mCallbacks;
    private List<Song> mSongsToShow;
    private MusicListAdapter mMusicListAdapter;

    public static MusicListFragment newInstance(Callbacks callbacks) {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacks = callbacks;
        fragment.setArguments(args);
        return fragment;
    }

    public static MusicListFragment newInstance(Callbacks callbacks, List<Song> songs) {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacks = callbacks;
        fragment.mSongsToShow = songs;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mSongsToShow == null) {
            mSongsToShow = SongRepository.newInstance(getActivity()).getSongs();
        }
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        mMusicListAdapter = MusicListAdapter.newInstance(
                getActivity(), mSongsToShow, ListFragment.States.MUSICS, mCallbacks);
        return mMusicListAdapter;
    }

    @Override
    public int getFragmtView() {
        return R.layout.fragment_music_list;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerview_songs;
    }

    public void setSongsToShow(List<Song> songsToShow) {
        mSongsToShow = songsToShow;
        if (mMusicListAdapter != null)
            mMusicListAdapter.notifyDataSetChanged();
    }
}