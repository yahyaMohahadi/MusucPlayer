package org.maktab.musucplayer.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentMusicListBinding;

public class MusicListFragment extends Fragment {
    private FragmentMusicListBinding mBinding;


    public static MusicListFragment newInstance() {

        MusicListFragment fragment = new MusicListFragment();
        return fragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music_list, container, false);
        return mBinding.getRoot();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}