package com.ivan.sison.redeem.views.dialogs;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.ivan.sison.redeem.R;
import com.ivan.sison.redeem.utils.AlertUtil;
import com.ivan.sison.redeem.utils.ConstantUtil;
import com.ivan.sison.redeem.utils.KeyUtil;
import com.ivan.sison.redeem.utils.ValidateUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginDialog extends DialogFragment implements View.OnClickListener {

    private View mView;
    private EditText mFieldUser, mFieldPass;

    private AlertUtil mAlert;
    private ProgressDialog mProgress;

    public LoginPopupInterface mInterface;

    public interface LoginPopupInterface {
        void onResult(int result);
    }

    public LoginDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_dialog, container, false);

        setupAlert();
        setupProgress();
        setupObj();

        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                TextInputLayout fieldLayoutUser = (TextInputLayout) mView.findViewById(R.id.field_layout_user);
                TextInputLayout fieldLayoutPass = (TextInputLayout) mView.findViewById(R.id.field_layout_pass);

                fieldLayoutUser.setError("");
                fieldLayoutPass.setError("");

                final String username = mFieldUser.getText().toString();
                final String password = mFieldPass.getText().toString();

                if (!ValidateUtil.isUsernameValid(username) &&
                        !ValidateUtil.isPasswordValid(password)) {
                    fieldLayoutUser.setError("Invalid Username");
                    fieldLayoutPass.setError("Invalid Password");
                }
                else if (!ValidateUtil.isUsernameValid(username)) {
                    fieldLayoutUser.setError("Invalid Username");
                }
                else if (!ValidateUtil.isPasswordValid(password)) {
                    fieldLayoutPass.setError("Invalid Password");
                }
                else {
                    onLogin(username, password);
                }
                break;
            case R.id.btn_cancel:
                mInterface.onResult(KeyUtil.RESULT_FAILED);
                dismiss();
                break;
            default:
                break;
        }
    }

    private void setupAlert() {
        mAlert = new AlertUtil(new AlertUtil.AlertUtilInterface() {
            @Override
            public void onClickedPositive(String message) {

            }

            @Override
            public void onClickedNegative(String message) {

            }
        });
    }

    private void setupProgress() {
        mProgress = new ProgressDialog(getContext());
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
    }

    private void setupObj() {
        mFieldUser = (EditText) mView.findViewById(R.id.field_user);
        mFieldPass = (EditText) mView.findViewById(R.id.field_pass);

        Button btnLogin = (Button) mView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        Button btnCancel = (Button) mView.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    private void onLogin(String username, String password) {
        mProgress.show();

        if (username.equals(ConstantUtil.ADMIN_USERNAME) &&
            password.equals(ConstantUtil.ADMIN_PASSWORD)) {
            onValidCredentials();
        }
        else {
            onInvalidCredentials();
        }
    }

    // MARK: - Helper
    public void onValidCredentials() {
        mProgress.dismiss();

        mInterface.onResult(KeyUtil.RESULT_SUCCESS);
        dismiss();
    }

    public void onInvalidCredentials() {
        mProgress.dismiss();

        mFieldUser.setText("");
        mFieldPass.setText("");

        onPromptMessage(getResources().getString(R.string.message_title_notice),
                getResources().getString(R.string.message_login_invalid),
                getResources().getString(R.string.message_positive_ok));
    }

    public void onFailedRequest(String message) {
        mProgress.dismiss();

        onPromptMessage(getResources().getString(R.string.message_title_notice),
                message,
                getResources().getString(R.string.message_positive_ok));
    }

    // MARK: - Util
    public void onPromptMessage(String title, String message, String positive) {
        mAlert.onShowAlert(getContext(), title, message, positive);
    }

    public void onPromptMessage(String title, String message, String positive, String negative) {
        mAlert.onShowAlert(getContext(), title, message, positive, negative);
    }
}

