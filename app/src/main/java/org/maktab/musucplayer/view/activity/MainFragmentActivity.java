package org.maktab.musucplayer.view.activity;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.ActivitySingleFragmentBinding;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.view_model.player.Music;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view.fragment.DitailMusicFragment;
import org.maktab.musucplayer.view.fragment.MainFragment;
import org.maktab.musucplayer.view.fragment.PlayingMusicFragment;
import org.maktab.musucplayer.view.fragment.lists.MusicListFragment;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainFragmentActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private ActivitySingleFragmentBinding mBinding;

    protected ListUtils.Callbacks mCallbacks;
    private Music mMusic;
    protected MainFragment mFragmentMian;
    protected DitailMusicFragment mFragmentDitails;
    protected PlayingMusicFragment mFragmentPlay;
    protected MusicListFragment mFragmentMusicList;
    protected MusicListFragment mFragmentMusicListArtist;
    protected MusicListFragment mFragmentMusicListAlbum;
    private StateOnlineFragment mStateOnline = StateOnlineFragment.MAIN;
    private ListUtils.States mStatesPrevious = ListUtils.States.MUSICS;
    private Bundle mBundlesavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        mBundlesavedInstanceState = savedInstanceState;
        requestPermissions();
    }

    private void doRunAppAfterPermision(Bundle savedInstanceState) {
        //todo run it in new thread or in database (initialisiation mabe get lot time)
        initMusicRepository();
        initCallBack();
        if (savedInstanceState == null) {
            initFragment();
        }
        showFragment(StateOnlineFragment.MAIN);
    }

    private void initFragment() {
        mFragmentMusicList = MusicListFragment.newInstance(mCallbacks, ListUtils.States.MUSICS);
        mFragmentMusicListAlbum = MusicListFragment.newInstance(mCallbacks, ListUtils.States.MUSIC_ALBUM);
        mFragmentMusicListArtist = MusicListFragment.newInstance(mCallbacks, ListUtils.States.MUSIC_ARTIST);
        mFragmentMian = MainFragment.newInstance(mCallbacks);
        mFragmentPlay = PlayingMusicFragment.newInstance();
        mFragmentDitails = DitailMusicFragment.newInstance();
    }

    private void initMusicRepository() {
        mMusic = Music.newInstance(
                getApplicationContext(),
                SongRepository.newInstance(getApplicationContext()).getSongs());

        mMusic.setCallbacksListtener(new Music.Callbacks() {
            @Override
            public void onMusicChangeListtener(Song song) {
                //todo change music curent song
                //mFragmentMusicList.changeCurentSong(song);
                mFragmentMian.setCurentSong(song);
            }
        });
    }

    private void initCallBack() {
        mCallbacks = new ListUtils.Callbacks() {
            @Override
            public void itemCalled(ListUtils.States states, String item) {
                mStatesPrevious = states;
                SongRepository repository = SongRepository.newInstance(getApplication());
                List<Song> songsCalled = repository.getListSong(states, item);
                switch (states) {
                    case ALBUMS:
                        mMusic.setSongList(songsCalled);
                        mFragmentMusicListAlbum.setSongs(songsCalled);
                        showFragment(StateOnlineFragment.MUSIC_ALBUM);
                        break;
                    case ARTISTS:
                        mMusic.setSongList(songsCalled);
                        mFragmentMusicListArtist.setSongs(songsCalled);
                        showFragment(StateOnlineFragment.MUSIC_ARTIST);
                        break;
                    case MUSICS:
                        mFragmentPlay.playSong(repository.getSongsById(Integer.parseInt(item)));
                        break;
                    case MUSIC_ARTIST:
                        mFragmentPlay.playSong(repository.getSongsById(Integer.parseInt(item)));
                        break;
                    case MUSIC_ALBUM:
                        mFragmentPlay.playSong(repository.getSongsById(Integer.parseInt(item)));
                        break;
                }
            }
        };
    }

    private void showFragment(StateOnlineFragment staet) {
        mStateOnline = staet;
        mFragmentPlay.update();
        switch (staet) {
            case MAIN: {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentMian).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
                break;
            }
            case DITAIL: {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentDitails).commit();
                getSupportFragmentManager().beginTransaction().remove(mFragmentPlay).commit();
                break;
            }
            case MUSIC_LIST: {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentMusicList).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
                break;
            }

            case MUSIC_ARTIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentMusicListArtist).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
                break;

            case MUSIC_ALBUM:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentMusicListAlbum).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        switch (mStateOnline) {
            case MAIN: {
                mMusic.deatach();
                super.onBackPressed();
                break;
            }
            case DITAIL: {
                if (mStatesPrevious == ListUtils.States.MUSICS) {
                    showFragment(StateOnlineFragment.MAIN);
                } else {
                    showFragment(StateOnlineFragment.MUSIC_LIST);
                }
                break;
            }
            case MUSIC_LIST: {
                showFragment(StateOnlineFragment.MAIN);
                break;
            }
        }
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
        DITAIL, MAIN, MUSIC_LIST, MUSIC_ARTIST, MUSIC_ALBUM
    }
}
