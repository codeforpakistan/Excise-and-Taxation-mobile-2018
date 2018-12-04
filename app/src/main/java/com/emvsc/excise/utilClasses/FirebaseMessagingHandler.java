package com.emvsc.excise.utilClasses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.emvsc.excise.R;
import com.emvsc.excise.javaClasses.WhmActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseMessagingHandler extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        handleMessage(remoteMessage.getData().toString());
    }

    private void handleMessage(String s) {

        Intent intent = new Intent(this, WhmActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        notificationBuilder.setContentTitle("Excise and Taxation Departement");
        Spanned spanned = Html.fromHtml("this is just description");
        notificationBuilder.setContentText(spanned);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSound(soundUri);
        notificationBuilder.setSmallIcon(R.drawable.app_icon);
        //notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.secp_logo));
        notificationBuilder.setAutoCancel(true);
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

    }
}
