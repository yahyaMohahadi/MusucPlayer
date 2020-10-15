package org.maktab.musucplayer.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.ActivitySingleFragmentBinding;
import org.maktab.musucplayer.view_model.MainViewModel;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class MainFragmentActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private ActivitySingleFragmentBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.setContext(this);
        mViewModel.setFragmentManager(getSupportFragmentManager());
        mViewModel.requestPermision(this);

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
}
