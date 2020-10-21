package org.maktab.musucplayer.ui.main.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.ActivitySingleFragmentBinding;
import org.maktab.musucplayer.service.MusicService;
import org.maktab.musucplayer.ui.bar.PlayingBarFragment;
import org.maktab.musucplayer.ui.ditails.DitailMusicFragment;
import org.maktab.musucplayer.ui.main.fragment.MainFragment;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private ActivitySingleFragmentBinding mBinding;
    private MainActivityViewModel mViewModel;


    private MainFragment mFragmentMain;
    private DitailMusicFragment mFragmentDetail;
    private PlayingBarFragment mFragmentPlay;
    private Callback mCallback;
    private OnlineFragment mOnlineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo dont send contex
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        mBinding.setLifecycleOwner(this);
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mViewModel.setCallbacks(getApplicationContext());
        requestPermissions();
        initCallbackBar();
        initFragments();
        setupMaianFragment();
        startService(MusicService.newIntent(this));
    }

    private void initCallbackBar() {
        mCallback = new Callback() {
            @Override
            public void onBarClicked() {
                setupDetailFragment();
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (mOnlineFragment == OnlineFragment.MAIN)
            super.onBackPressed();
        else
            setupMaianFragment();
    }

    private void initFragments() {
        //todo init in asyncronize way
        mFragmentMain = MainFragment.newInstance(mViewModel.getCallbacks());
        mFragmentDetail = DitailMusicFragment.newInstance();
        mFragmentPlay = PlayingBarFragment.newInstance(mCallback);
    }

    private void requestPermissions() {
        String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            return;

        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, 256, perms)
                            //.setRationale(R.string.camera_and_location_rationale)
                            .setPositiveButtonText(android.R.string.ok)
                            //.setNegativeButtonText(android.R.string.cancel)
                            //.setTheme(R.style.my_fancy_style)
                            .build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        this.finish();
    }

    public void setupMaianFragment() {
        mOnlineFragment = OnlineFragment.MAIN;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentMain).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
    }

    public void setupDetailFragment() {
        mOnlineFragment = OnlineFragment.DITAIL;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentDetail).commit();
        getSupportFragmentManager().beginTransaction().remove(mFragmentPlay).commit();
    }


    private MusicService mService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((MusicService.LocalBinder) iBinder).getService();
            mViewModel.setMusic(mService);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = MusicService.newIntent(this);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        startService(MusicService.newIntent(this, MusicService.KEY_STRING_stop));
    }

    public interface Callback {
        void onBarClicked();
    }

    public enum OnlineFragment {
        MAIN, DITAIL
    }
}
