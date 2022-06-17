package uz.ilmhona.kuron_uz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        Log.d("fcm", "Refreshed token: $p0")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(p0)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d("fcm", "send the token to server: $token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.d("fcm", "data is not null")

        val type = p0.data["type"]
        val title = p0.data["title"]
        val body = p0.data["message"]
        val appUrl = p0.data["app_url"]
        val img = p0.data["image"]
        val bottomTitle = p0.data["bottom_title"]

        val sPref = getSharedPreferences("app", Context.MODE_PRIVATE)

        val edit = sPref.edit()
        edit.putString("type", type)
        edit.putString("title", title)
        edit.putString("message", body)
        edit.putString("app_url", appUrl)
        edit.putString("image", img)
        edit.putString("bottom_title", bottomTitle)

        if (type == "") {
            edit.putString("title", "")
        }

        edit.apply()

        sendNotification(body!!, title!!, appUrl!!,img!!, type!!)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(
            strBody: String,
            title: String,
            appUrl: String,
            img: String,
            type: String) {

        val openUrl = Intent(Intent.ACTION_VIEW)
        openUrl.data = Uri.parse(appUrl)

        val intent = if(type=="package") {
            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appUrl"))
        } else if (type == "url") {
            openUrl
        } else {
            Intent(this, MainActivity::class.java)
        }

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.FLAG_MUTABLE else PendingIntent.FLAG_UPDATE_CURRENT

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, flag)


        val channelId = "channel_id_namaz"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(strBody)
                .setVibrate(LongArray(0))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setStyle(NotificationCompat.BigTextStyle().bigText(strBody))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setChannelId(channelId)
                .setSmallIcon(R.drawable.ic_main_round)
                .setColorized(true)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_main_round))
                .setLargeIcon(getBitmapFromURL(img))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "FCM Channel Namaz",
                    NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.lightColor = ContextCompat.getColor(applicationContext, R.color.teal_200)
            notificationManager.createNotificationChannel(channel)

        }
        val notification = notificationBuilder.build()

        notificationManager.notify(0 /* ID of notification */, notification)

    }

    fun getBitmapFromURL(src: String): Bitmap? {
        try {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            return null
        }

    }
}