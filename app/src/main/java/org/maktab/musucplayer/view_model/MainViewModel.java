package org.maktab.musucplayer.view_model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.player.Music;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.view_model.orderingFragment.ViewModelOrdering;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainViewModel extends ViewModel {
    private Music mMusic;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private ViewModelOrdering mOrderingFragments;

    public void setContext(Context context) {
        mContext = context;
        if (mMusic==null){
            mMusic = Music.newInstance(mContext , SongRepository.newInstance(context).getSongs());
        }
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
        if (mOrderingFragments == null)
            mOrderingFragments = ViewModelOrdering.newInstance(fragmentManager);
        mOrderingFragments.setupMain();
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

}
