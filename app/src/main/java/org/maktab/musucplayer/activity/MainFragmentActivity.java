package org.maktab.musucplayer.activity;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.database.SongRepository;
import org.maktab.musucplayer.fragment.DitailMusicFragment;
import org.maktab.musucplayer.fragment.MainFragment;
import org.maktab.musucplayer.fragment.PlayingMusicFragment;
import org.maktab.musucplayer.fragment.lists.ListFragment;
import org.maktab.musucplayer.fragment.lists.MusicListFragment;
import org.maktab.musucplayer.model.Song;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainFragmentActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    protected ListFragment.Callbacks mCallbacks;

    protected MainFragment mFragmentMian;
    protected DitailMusicFragment mFragmentDitails;
    protected PlayingMusicFragment mFragmentPlay;
    protected MusicListFragment mFragmentMusicList;
    private StateOnlineFragment stateOnline = StateOnlineFragment.MAIN;
    private Bundle mBundlesavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        mBundlesavedInstanceState = savedInstanceState;
        requestPermissions();

    }

    private void doRunAppAfterPermision(Bundle savedInstanceState) {
        initCallBack();
        if (savedInstanceState == null) {
            mFragmentMusicList = MusicListFragment.newInstance(mCallbacks);
            mFragmentMian = MainFragment.newInstance(mCallbacks);
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.add(R.id.main_fragment_place, mFragmentMian).commit();

            //todo get the closed database option and puse it to playfragment list
            mFragmentPlay = PlayingMusicFragment
                    .newInstance(SongRepository.newInstance(getApplicationContext())
                            .getSongs()
                    );
            mFragmentDitails = DitailMusicFragment.newInstance(null);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frgment_playing_place, mFragmentPlay)
                    .commit();
        }
    }

    private void initCallBack() {
        mCallbacks = new ListFragment.Callbacks() {
            @Override
            public void itemCalled(ListFragment.States states, String item) {
                List<Song> songsCalled = SongRepository.newInstance(getApplication()).getListSong(states, item);
                if (songsCalled.size() == 1) {
                    stateOnline = StateOnlineFragment.DITAIL;
                    mFragmentDitails.updateSong(songsCalled.get(0));
                    mFragmentPlay.updateList(songsCalled);
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.main_fragment_place, mFragmentDitails).commit();
                } else {
                    stateOnline = StateOnlineFragment.SONGS;
                    mFragmentMusicList.setSongsToShow(songsCalled);
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.main_fragment_place, mFragmentMusicList).commit();
                }

            }
        };
    }

    private void setMainFragment() {
        stateOnline = StateOnlineFragment.MAIN;
        Fragment fragment = MainFragment.newInstance(mCallbacks);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.main_fragment_place, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        if (stateOnline == StateOnlineFragment.DITAIL || stateOnline == StateOnlineFragment.SONGS) {
            setMainFragment();
        } else
            super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        doRunAppAfterPermision(mBundlesavedInstanceState);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        this.finish();
    }

    private void requestPermissions() {
        String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            doRunAppAfterPermision(mBundlesavedInstanceState);

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

    public enum StateOnlineFragment {
        DITAIL, MAIN, SONGS
    }
}

