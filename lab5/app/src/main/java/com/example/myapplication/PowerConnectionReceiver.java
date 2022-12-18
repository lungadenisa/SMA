package com.example.myapplication;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
public class PowerConnectionReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView level = ((MainActivity) context).findViewById(R.id.level);
            TextView charging = ((MainActivity) context).findViewById(R.id.charging);
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);

            //Determinarea procentajului bateriei
            int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int percentage = batteryLevel * 100 / scale;
            level.setText("The percentage of the battery: " + percentage + "%");

            //Determinam daca telefonul este la incarcat sau nu
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            // Determinam cum incarcam telefonul
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if (isCharging && usbCharge) {
                charging.setText("The phone is charging using USB");
            } else if (isCharging && acCharge) {
                charging.setText("The phone is charging using AC");
            } else {
                charging.setText("The phone is not charging");
            }

            Intent newIntent = new Intent(context, MainActivity.class);
            newIntent.putExtra("status", charging.getText());
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newIntent, 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Charging status changed!")
                    .setContentText(charging.getText())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(MainActivity.notification, mBuilder.build());
        }
}