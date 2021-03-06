/*          Copyright (c) 2015 Krishanu Dutta Bhuyan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.kdb.ledcontrol;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by KDB on 03/01/2015.
 */
public class BootReceiver extends BroadcastReceiver {

    private String SHARED_PREF;
    private static final String PREF_SET_ON_BOOT = "set_on_boot";
    private static final String TAG = "LED Control";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i(TAG, "Received BOOT_COMPLETED");
        SHARED_PREF = context.getPackageName() + ".prefs";
        boolean run = context.getSharedPreferences(SHARED_PREF, 0).getBoolean(PREF_SET_ON_BOOT, false);
        if (run) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    new LEDManager(context).setOnBoot();
                    return null;
                }
            }.execute();
        }
    }


}
