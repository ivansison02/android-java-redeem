package com.ivan.sison.redeem.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class PermissionUtil {

    private Activity mActivity;

    public PermissionUtil(Activity activity) {
        this.mActivity = activity;
    }

    public boolean onPermissionNeeded() {
        if (checkPermissionForReadExternalStorage() || checkPermissionForWriteExternalStorage()
                || needPermissionForCamera()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onRequestPermission(int code) {
        if (code == KeyUtil.CODE_PERMISSION_CAMERA) {
            ActivityCompat.requestPermissions(mActivity, getPermissions(), KeyUtil.CODE_PERMISSION_CAMERA);
        }
        else {
            ActivityCompat.requestPermissions(mActivity, getPermissions(), KeyUtil.CODE_PERMISSION);
        }
    }

    public boolean needPermissionForCamera() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkPermissionForWriteExternalStorage() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkPermissionForReadExternalStorage() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    private String[] getPermissions() {
        ArrayList<String> permissionList = new ArrayList<>();

        if (needPermissionForCamera()) {
            permissionList.add(Manifest.permission.CAMERA);
        }

        if (checkPermissionForReadExternalStorage()) {
            permissionList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (checkPermissionForWriteExternalStorage()) {
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        String[] permissions = new String[permissionList.size()];

        for (int i = 0; i < permissionList.size(); i++) {
            permissions[i] = permissionList.get(i);
        }

        return permissions;
    }
}
