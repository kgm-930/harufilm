package com.example.todayfilm

import android.util.Log
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

        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        val notificationBuilder = NotificationCompat.Builder(this, "c207")
            .setSmallIcon(R.drawable.heart).setContentTitle(title).setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        NotificationManagerCompat.from(this).notify(1,notificationBuilder.build())
    }
}