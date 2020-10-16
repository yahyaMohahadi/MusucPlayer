package org.maktab.musucplayer.ui.ditails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentDitailBinding;


public class DitailMusicFragment extends Fragment {
    private FragmentDitailBinding mBinding;

    public static DitailMusicFragment newInstance() {
        DitailMusicFragment fragment = new DitailMusicFragment();
        Bundle arg = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ditail, container, false);
        return mBinding.getRoot();
    }
}



