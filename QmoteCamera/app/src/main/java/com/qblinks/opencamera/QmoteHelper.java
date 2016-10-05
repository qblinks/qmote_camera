package com.qblinks.opencamera;

import android.content.Intent;
import android.util.Log;

public class QmoteHelper {

    public static String TAG = "QmoteHelper" ;
    public static boolean DEBUG = false ;
    public static final String QMOTE_ACTION = "QmoteAction" ;

    public static String checkThenGetQmoteAction(Intent intent){
        if(intent == null){
            return null;
        }

        String qmoteAction = intent.getStringExtra(QMOTE_ACTION);
        if(qmoteAction == null){
            debugLog("Qmote action is null.");
            return null;
        }
        return qmoteAction;
    }

    private static void debugLog(String message){
        if(DEBUG){
            Log.i(TAG,message);
        }
    }
}
