package org.maktab.musucplayer.ui.bar;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentPlayingMusicBinding;


public class PlayingMusicFragment extends Fragment {
    private FragmentPlayingMusicBinding mBinding;
    public static final int LIMIT_TITTLE_PLAY_BAR = 30;

    public static PlayingMusicFragment newInstance() {
        PlayingMusicFragment fragment = new PlayingMusicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playing_music, container, false);


        return mBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
