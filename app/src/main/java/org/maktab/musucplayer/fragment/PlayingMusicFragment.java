package org.maktab.musucplayer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;


public class PlayingMusicFragment extends Fragment {


    public static PlayingMusicFragment newInstance() {
        PlayingMusicFragment fragment = new PlayingMusicFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        view = inflater.inflate(R.layout.fragment_playing_music, container, false);


        return view;
    }

}
