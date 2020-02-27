package com.ivan.sison.redeem.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;

import com.ivan.sison.redeem.R;

public class AlertUtil {

    private AlertDialog mDialog;
    private AlertUtilInterface mInterface;

    public interface AlertUtilInterface {
        void onClickedPositive(String message);
        void onClickedNegative(String message);
    }

    public AlertUtil(AlertUtilInterface mInterface) {
        this.mInterface = mInterface;
    }

    public void onShowAlert(Context context, String title, final String message,
                            String positive) {
        AlertDialog.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        }
        else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setIcon(android.R.drawable.ic_dialog_alert);

        if (message.toLowerCase().contains("success") ||
                message.toLowerCase().contains("refresh")) {
            builder.setIcon(R.drawable.ic_check);
        }

        builder.setCancelable(false);
        mDialog = builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mInterface.onClickedPositive(message);
                    }
                })
                .show();
    }

    public void onShowAlert(Context context, String title, final String message,
                            String positive, String negative) {
        AlertDialog.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        }
        else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setCancelable(false);
        mDialog = builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mInterface.onClickedPositive(message);
                    }
                })
                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mInterface.onClickedNegative(message);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onDismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
