package br.com.ricardooliveira.exemplodenotificaes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exibirNotificacao(View view) {

        private static final String CHANNEL_ID = "alertas";

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID,
                getString(R.string.notification_name),
                NotificationManager.IMPORTANCE_HIGH
        );

        notificationChannel.setDescription(getString(R.string.notification_descripiton));
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.MAGENTA);
        notificationChannel.setVibrationPattern(new long[]{0, 100, 500, 100});
        notificationChannel.enableVibration(true);

        notificationManager.createNotificationChannel(notificationChannel);

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this, CHANNEL_ID);

        notification.setContentTitle(getString(R.string.notification_title));
        notification.setChannelId(CHANNEL_ID);
        notification.setContentText(getString(R.string.notification_content));
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentIntent(PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT));

        notificationManager.notify(100, notification.build());

    }
}
