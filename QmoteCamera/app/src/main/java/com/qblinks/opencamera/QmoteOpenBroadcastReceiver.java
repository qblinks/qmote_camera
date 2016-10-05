package com.qblinks.opencamera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class QmoteOpenBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String qmoteAction = QmoteHelper.checkThenGetQmoteAction(intent);
        if(qmoteAction == null){
            return ;
        }

        if(qmoteAction.equals(QmoteAction.OPEN)){
            startQmoteMainActivity(context);
            wakeUpScreenOn(context);
        }
    }

    private void startQmoteMainActivity(Context context){
        Intent mainIntent = new Intent(context, QmoteMainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.getApplicationContext().startActivity(mainIntent);
    }

    private void wakeUpScreenOn(Context conext){
        PowerManager powerManager = (PowerManager) conext.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,"TAG");
        wakeLock.acquire();
    }
}
