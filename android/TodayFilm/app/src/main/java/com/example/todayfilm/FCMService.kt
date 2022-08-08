package com.example.todayfilm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


open class FCMService : FirebaseMessagingService() {
    override fun onNewToken(@NonNull token: String) {
        super.onNewToken(token)
        //token을 서버로 전송
    }

    override fun onMessageReceived(@NonNull remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title: String? = remoteMessage.data["title"]
        val message: String? = remoteMessage.data["message"]

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationManager : NotificationManager = getSystemService (Context.NOTIFICATION_SERVICE) as NotificationManager
            val  notificationChannel = NotificationChannel(
                "c207",
                "c207",
                NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel.setDescription("c207");
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel);
        }

        val notificationBuilder = NotificationCompat.Builder(this, "c207")
            .setSmallIcon(R.drawable.logo).setContentTitle(title).setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        NotificationManagerCompat.from(applicationContext).notify(1, notificationBuilder.build())
    }
}