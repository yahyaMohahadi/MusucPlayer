package org.maktab.musucplayer.view_model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.player.Music;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.view_model.orderingFragment.ViewModelOrdering;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainViewModel extends ViewModel {
    private Activity mContext;
    private FragmentManager mFragmentManager;
    private ViewModelOrdering mOrderingFragments;
    private MusicViewModel mMusicViewModel ;
    private MutableLiveData mMutableLiveData = new MutableLiveData();


    public void setContext(final Activity context) {
        mContext = context;
        if (mMusicViewModel == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    firstInitialisationForMusicsViewModel(context);
                }
            }).start();
        }
    }

    public void firstInitialisationForMusicsViewModel(Context context) {
        mMusicViewModel = MusicViewModel.newInstance();
        mMutableLiveData = SongRepository.newInstance(context).getLiveSongs();

    }

    public MutableLiveData getMutableLiveData() {
        return mMutableLiveData;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
        mOrderingFragments = ViewModelOrdering.newInstance(fragmentManager);
        mOrderingFragments.setupDetal();
    }

    public static void requestPermision(Activity activity) {
        String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(activity, perms)) {
        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(activity, 256, perms)
                            .setPositiveButtonText(android.R.string.ok)
                            .setNegativeButtonText(android.R.string.cancel)
                            .setTheme(R.style.Theme_MaterialComponents_Light_Dialog_Alert_Bridge)
                            .build());
        }
    }

    public void updateMusics() {
        mMusicViewModel.getMutableLiveDataAllSongs().setValue((List<Song>) mMutableLiveData.getValue());
        Music.newInstance(mContext, (List<Song>) SongRepository.newInstance(mContext).getLiveSongs());
    }
}
