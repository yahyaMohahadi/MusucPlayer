package org.maktab.musucplayer.ui.main;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.ActivitySingleFragmentBinding;
import org.maktab.musucplayer.ui.bar.PlayingMusicFragment;
import org.maktab.musucplayer.ui.ditails.DitailMusicFragment;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private ActivitySingleFragmentBinding mBinding;
    private MainFragment mFragmentMain;
    private DitailMusicFragment mFragmentDetail;
    private PlayingMusicFragment mFragmentPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        requestPermissions();
        initFragments();
        setupMaianFragment();
    }

    private void initFragments() {
        //todo init in asyncronize way
        mFragmentMain = MainFragment.newInstance();
        mFragmentDetail = DitailMusicFragment.newInstance();
        mFragmentPlay = PlayingMusicFragment.newInstance();
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
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentMain).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
    }

    public void setupDetailFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_place, mFragmentDetail).commit();
        getSupportFragmentManager().beginTransaction().remove(mFragmentPlay);
    }
}
