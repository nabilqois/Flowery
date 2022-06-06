package com.nabil.flowery.receiver

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.nabil.flowery.R

const val notificationID = 1
const val channelID = "channel1"


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notification: Notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_baseline_chevron_right_24)
            .setContentTitle("Menyiram Tanaman")
            .setContentText("Mari menyiram tanaman!")
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID,notification)
    }
}