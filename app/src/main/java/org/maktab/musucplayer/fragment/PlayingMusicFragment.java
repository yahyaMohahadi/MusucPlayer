package org.maktab.musucplayer.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.fragment.lists.ListFragment;
import org.maktab.musucplayer.model.Song;

import java.io.Serializable;
import java.util.List;


public class PlayingMusicFragment extends Fragment {

    public static final String KEY_STATE = "org.maktab.musucplayer.fragmentSTATE";
    public static final String KEY_SONGS = "org.maktab.musucplayer.fragmentSONGS";
    private ListFragment.States mStates;
    private List<Song> mSongs;

    public static PlayingMusicFragment newInstance(ListFragment.States state, List<Song> songs) {
        PlayingMusicFragment fragment = new PlayingMusicFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_STATE, state);
        args.putSerializable(KEY_SONGS, (Serializable) songs);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStates = (ListFragment.States) getArguments().getSerializable(KEY_STATE);
            mSongs = (List<Song>) getArguments().getSerializable(KEY_SONGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing_music, container, false);
        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(),mSongs.get(0).getUri());
        mediaPlayer.start();
        return view;
    }


}
