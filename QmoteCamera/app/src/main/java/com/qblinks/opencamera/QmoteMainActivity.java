package com.qblinks.opencamera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import net.sourceforge.opencamera.MainActivity;

public class QmoteMainActivity extends MainActivity {

    private CommandBroadcastReceiver mQmoteCmdBroadcastReceiver;
    private static final String QMOTE_RECEIVER_FILTER_ACTION = "QmoteCommand" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        unregisterQmoteCommand();
        sendCameraCloseCallback();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetToLastCameraId();
        registerQmoteCommand();
    }

    @Override
    public void clickedSwitchCamera(View view) {
        super.clickedSwitchCamera(view);
        int switchToCameraId = getApplicationInterface().getCameraIdPref();
        SettingsManager.saveCameraId(this, switchToCameraId);
    }

    private void resetToLastCameraId() {
        int cameraId = SettingsManager.getCameraId(this);
        getPreview().setCamera(cameraId);
    }

    private void registerQmoteCommand() {
        if (mQmoteCmdBroadcastReceiver == null) {
            mQmoteCmdBroadcastReceiver = new CommandBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter(QMOTE_RECEIVER_FILTER_ACTION);
        registerReceiver(mQmoteCmdBroadcastReceiver, intentFilter);
    }

    private void unregisterQmoteCommand() {
        if (mQmoteCmdBroadcastReceiver == null) {
            return;
        }
        unregisterReceiver(mQmoteCmdBroadcastReceiver);
    }

    private void sendCameraCloseCallback(){
        QmoteCallbackHelper.sendCallbackCameraClose(this);
    }

    class CommandBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String qmoteAction = QmoteHelper.checkThenGetQmoteAction(intent);
            if(qmoteAction == null){
                return;
            }

            if (qmoteAction.equals(QmoteAction.TAKE_PICTURE)) {
                QmoteMainActivity.this.clickedTakePhoto(null);
            } else if (qmoteAction.equals(QmoteAction.FINISH)) {
                QmoteMainActivity.this.finish();
            }
        }
    }
}
