package org.maktab.musucplayer.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class RequestPermitiion implements EasyPermissions.PermissionCallbacks {
    private final CallbackPermision mCallbackPermision;
    private Activity mActivity;

    public RequestPermitiion(Activity Activity, CallbackPermision callbackPermision) {
        mCallbackPermision = callbackPermision;
        mActivity = Activity;
    }



    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        mCallbackPermision.onPermisionResoult(true);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        mCallbackPermision.onPermisionResoult(false);
    }

    public void requestPermissions(String[] params) {

        if (EasyPermissions.hasPermissions(mActivity, params)) {
            mCallbackPermision.onPermisionResoult(true);

        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(mActivity, 256, params)
                            //.setRationale(R.string.camera_and_location_rationale)
                            .setPositiveButtonText(android.R.string.ok)
                            //.setNegativeButtonText(android.R.string.cancel)
                            //.setTheme(R.style.my_fancy_style)
                            .build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,mActivity);
    }

    public interface CallbackPermision {
        void onPermisionResoult(boolean result);
    }
}
