package com.qblinks.opencamera;

import android.content.Context;
import android.content.Intent;

public class QmoteCallbackHelper {

    private static final String QMOTE_ACTION_NAME = "QmoteCallBackCommand" ;
    private static final String QMOTE_ACTION = "QmoteAction" ;
    private static final String QMOTE_CALLBACK_CAMERA_READY = "CameraReady";
    private static final String QMOTE_CALLBACK_CAMERA_CLOSE = "CameraClose";

    public static void sendCallbackCameraReady(Context context){
        Intent intent = new Intent(QMOTE_ACTION_NAME);
        intent.putExtra(QMOTE_ACTION,QMOTE_CALLBACK_CAMERA_READY);
        context.sendBroadcast(intent);
    }

    public static void sendCallbackCameraClose(Context context){
        Intent intent = new Intent(QMOTE_ACTION_NAME);
        intent.putExtra(QMOTE_ACTION,QMOTE_CALLBACK_CAMERA_CLOSE);
        context.sendBroadcast(intent);
    }
}
