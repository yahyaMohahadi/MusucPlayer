package org.maktab.musucplayer.ui.bar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.databinding.FragmentPlayingBarMusicBinding;
import org.maktab.musucplayer.service.MusicService;
import org.maktab.musucplayer.service.player.Music;
import org.maktab.musucplayer.ui.main.activity.MainActivity;


public class PlayingBarFragment extends Fragment {
    private MusicService mService;
    private FragmentPlayingBarMusicBinding mBinding;
    private BarViewModel mViewModel;
    private MainActivity.Callback mCallback;
    private boolean mBound;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((MusicService.LocalBinder) iBinder).getService();
            mBound = true;
            mViewModel.fetchService(mService);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };


    public static PlayingBarFragment newInstance(MainActivity.Callback callback) {
        PlayingBarFragment fragment = new PlayingBarFragment();
        fragment.mCallback = callback;
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playing_bar_music, container, false);
        mBinding.setLifecycleOwner(this);
        mBinding.setCallback(mCallback);
        mViewModel = new ViewModelProvider(this).get(BarViewModel.class);
        mBinding.setVeiwModel(mViewModel);
        mViewModel.fechMusic(this);
        return mBinding.getRoot();
    }


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
    }

}
