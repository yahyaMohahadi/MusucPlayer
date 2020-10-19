package org.maktab.musucplayer.ui.bar;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.databinding.FragmentPlayingBarMusicBinding;


public class PlayingBarFragment extends Fragment {
    private FragmentPlayingBarMusicBinding mBinding;
    private BarViewModel mViewModel;


    public static PlayingBarFragment newInstance() {
        PlayingBarFragment fragment = new PlayingBarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playing_bar_music, container, false);
        mBinding.setLifecycleOwner(this);
        mViewModel = new ViewModelProvider(this).get(BarViewModel.class);
        mBinding.setVeiwModel(mViewModel);
        mViewModel.fetchMusic(this.getActivity(),this);
        return mBinding.getRoot();
    }

}
