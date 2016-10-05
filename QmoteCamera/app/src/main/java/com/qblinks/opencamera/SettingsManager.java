package com.qblinks.opencamera;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private final static String QBLINKS_PREF ="QblinksPref" ;
    private final static String PREF_CAMERA_ID = "CameraId" ;

    private static SharedPreferences getQblinksSharedPreferences(Context context){
        return context.getSharedPreferences(QBLINKS_PREF,0);
    }

    public static void saveCameraId(Context context,int cameraId){
        SharedPreferences settings = getQblinksSharedPreferences(context);
        settings.edit().putInt(PREF_CAMERA_ID,cameraId).commit();
    }
    public static int getCameraId(Context context){
        SharedPreferences settings = getQblinksSharedPreferences(context);
        return settings.getInt(PREF_CAMERA_ID,0);
    }
}
