package com.layout.chiindianatour;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        //Called when Chicago intent is received
        if(action.equals("com.layout.travel.intentChicago")){
            Intent intentChi = new Intent();
            intentChi.setClassName(context,"com.layout.chiindianatour.ChiTown");
            intentChi.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentChi);
        }
        //Called when Indianapolis intent is received
        if(action.equals("com.layout.travel.intentIndiana")){
            Intent intentInd = new Intent();
            intentInd.setClassName(context,"com.layout.chiindianatour.IndianaTown");
            intentInd.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentInd);
        }
    }
}
