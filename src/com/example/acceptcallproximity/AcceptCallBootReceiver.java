package com.example.acceptcallproximity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AcceptCallBootReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
                AcceptCallNotifier notifier = new AcceptCallNotifier(context);
                notifier.updateNotification();
        }

}

