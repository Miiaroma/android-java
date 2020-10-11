package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class ApmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        /*boolean state = intent.getBooleanExtra("state", false);
        if (state) {
            Toast.makeText(context, "Airplane mode is ON", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Airplane mode is OFF", Toast.LENGTH_SHORT).show();

        }*/

        if (isApmOn(context)) {
            Toast.makeText(context, "Airplane mode is ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Airplane mode is OFF", Toast.LENGTH_SHORT).show();
        }

    }
    public static boolean isApmOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON,0) != 0;
    }
}
