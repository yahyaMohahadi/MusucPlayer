package org.maktab.musucplayer.ui.ditails;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentDitailBinding;
import org.maktab.musucplayer.service.MusicService;


public class DitailMusicFragment extends Fragment {
    private FragmentDitailBinding mBinding;
    private DitailViewModel mViewModel;

    public static DitailMusicFragment newInstance() {
        DitailMusicFragment fragment = new DitailMusicFragment();
        Bundle arg = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().startService(MusicService.newIntent(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ditail, container, false);
        mBinding.setLifecycleOwner(this);
        mViewModel = new ViewModelProvider(this).get(DitailViewModel.class);
        mViewModel.fetchSeeckBar(this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    private boolean mBound;
    private MusicService mService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((MusicService.LocalBinder) iBinder).getService();
            mBound = true;
            mViewModel.fetchMusic(mService);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = MusicService.newIntent(getActivity());
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unbindService(connection);
        getActivity().startService(MusicService.newIntent(getActivity(), MusicService.KEY_STRING_stop));
        mBound = false;
    }

}



