package com.ivan.sison.redeem.services.scanner;

import android.content.Context;
import android.util.Log;

import com.google.zxing.Result;
import com.ivan.sison.redeem.utils.ConstantUtil;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ZxingScannerHelper implements ZXingScannerView.ResultHandler {

    private Context mContext;

    private boolean mHasStarted = false;

    private ZXingScannerView mScannerView;
    private ZxingScannerInterface mInterface;

    public interface ZxingScannerInterface {
        void onDetected(Result result);
    }

    public ZxingScannerHelper(Context context, ZxingScannerInterface mInterface) {
        this.mContext = context;
        this.mInterface = mInterface;
    }

    public void setScanner(ZXingScannerView scanner) {
        mScannerView = new ZXingScannerView(mContext);
        scanner.addView(mScannerView);
    }

    public ZXingScannerView getScannerView() {
        return mScannerView;
    }

    public void onResumeCamera() {
        if (!mHasStarted) {
            Log.e(ConstantUtil.TAG, "ZXING: Starting Camera");

            if (mScannerView == null) {
                mScannerView = new ZXingScannerView(mContext);
            }

            mScannerView.setResultHandler(this);
            mScannerView.startCamera();

            mHasStarted = true;
        }
        else {
            Log.e(ConstantUtil.TAG, "ZXING: Resuming Camera");
            mScannerView.resumeCameraPreview(this);
        }
    }

    public void onPauseCamera() {
        Log.e(ConstantUtil.TAG, "ZXING: Pausing Camera");

        mScannerView.stopCameraPreview();
    }

    public void onDestroyCamera() {
        Log.e(ConstantUtil.TAG, "ZXING: Destroying Camera");

        mScannerView.stopCameraPreview();
    }


    @Override
    public void handleResult(Result result) {
        mInterface.onDetected(result);
    }
}
