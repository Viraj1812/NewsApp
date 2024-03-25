package com.moengage.newsapp.utils
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.moengage.newsapp.R

object NotificationUtils {

    private const val CHANNEL_ID = "FCM_CHANNEL_ID"
    private const val CHANNEL_NAME = "FCM Channel"

    fun displayNotification(context: Context, title: String?, body: String?) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.baseline_add_alert_24)
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }
}
