package com.ivan.sison.redeem.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ivan.sison.redeem.R;
import com.ivan.sison.redeem.utils.AlertUtil;
import com.ivan.sison.redeem.utils.CacheUtil;
import com.ivan.sison.redeem.utils.DummyUtil;
import com.ivan.sison.redeem.utils.KeyUtil;
import com.ivan.sison.redeem.utils.PermissionUtil;
import com.ivan.sison.redeem.views.dialogs.LoginDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLogin, mBtnSettings;

    private AlertUtil mAlert;
    private CacheUtil mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*setupToolbar();
        */

        setupUtils();
        setupObj();
        setupPermission();

        //mHelper.onStoreEventsId();
        onViewManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (mCache.hasSavedEvents()) onShowLogin();
                else onPromptMessage(getResources().getString(R.string.message_title_notice),
                        getResources().getString(R.string.message_event_no_saved_list),
                        getResources().getString(R.string.message_positive_ok));
                break;
            case R.id.btn_settings:
                onShowAuth();
                break;
            default:
                break;
        }
    }

    private void setupUtils() {
        mAlert = new AlertUtil(new AlertUtil.AlertUtilInterface() {
            @Override
            public void onClickedPositive(String message) {

            }

            @Override
            public void onClickedNegative(String message) {

            }
        });

        mCache = new CacheUtil(this);
    }

    private void setupObj() {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);

        mBtnSettings = (Button) findViewById(R.id.btn_settings);
        mBtnSettings.setOnClickListener(this);
    }

    private void setupPermission() {
        PermissionUtil permission = new PermissionUtil(this);

        if (permission.onPermissionNeeded()) {
            permission.onRequestPermission(KeyUtil.CODE_PERMISSION);
        }
    }

    private void onViewManager() {
        if (mCache.getUser() == null) {
            mCache.onUpdateCache(DummyUtil.getUser());
            mCache.onUpdateCache(DummyUtil.getSavedEvents());
        }
        else if (!mCache.getUser().getUsername().isEmpty()) {
            startActivity(new Intent(MainActivity.this, ScanActivity.class));
        }
    }

    private void onShowLogin() {
        mBtnLogin.setEnabled(false);
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    private void onShowAuth() {
        mBtnSettings.setEnabled(false);
        LoginDialog loginPopup = new LoginDialog();
        loginPopup.mInterface = new LoginDialog.LoginPopupInterface() {
            @Override
            public void onResult(int result) {
                if (result == KeyUtil.RESULT_SUCCESS) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                }
                else {
                    mBtnSettings.setEnabled(true);
                }
            }
        };

        loginPopup.setCancelable(false);
        loginPopup.show(getSupportFragmentManager(), "");
    }

    // MARK: - Util
    public void onPromptMessage(String title, String message, String positive) {
        mAlert.onShowAlert(this, title, message, positive);
    }

    public void onPromptMessage(String title, String message, String positive, String negative) {
        mAlert.onShowAlert(this, title, message, positive, negative);
    }
}
