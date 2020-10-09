package org.maktab.musucplayer.fragment.lists;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.Music;

import java.util.List;

public class MusicListFragment extends ListFragment {
    private Callbacks mCallbacks;
    private List<Song> mSongsToShow;
    private MusicListAdapter mMusicListAdapter;
    private States mStates;

    public static MusicListFragment newInstance(Callbacks callbacks, States states) {
        if (states == States.MUSIC_ALBUM || states == States.MUSICS || states == States.MUSIC_ARTIST) {
            MusicListFragment fragment = new MusicListFragment();
            fragment.mCallbacks = callbacks;
            fragment.mStates = states;
            try {
                fragment.mSongsToShow = Music.newInstance().getSongList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fragment;
        }
        return null;
    }

    @Override
    public void updateList() {
        try {
            mMusicListAdapter.setSongs(Music.newInstance().getSongList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mSongsToShow == null) {
            mSongsToShow = SongRepository.newInstance(getActivity()).getSongs();
        }
    }

    public States getStates() {
        return mStates;
    }

    public void setStates(Context context, States states) {
        //todo why adapter is null fix
        mStates = states;
        if (mMusicListAdapter==null){
            mMusicListAdapter = MusicListAdapter.newInstance(
                    context, mSongsToShow, mStates, mCallbacks);
            return;
        }

        mMusicListAdapter.setState(states);
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        mRecyclerViewSongs.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mMusicListAdapter = MusicListAdapter.newInstance(
                getActivity(), mSongsToShow, mStates, mCallbacks);
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

    public void changeCurentSong(Song song) {
        if (mMusicListAdapter != null)
            mMusicListAdapter.setIntCurentSong(mSongsToShow.indexOf(song));
    }
}