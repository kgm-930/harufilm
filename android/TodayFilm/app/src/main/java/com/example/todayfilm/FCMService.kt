package com.example.todayfilm

import android.util.Log
import android.widget.Toast
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

        val title : String? = remoteMessage.data["title"]
        val message : String? = remoteMessage.data["message"]

        val notificationBuilder = NotificationCompat.Builder(this, "c207")
            .setSmallIcon(R.drawable.logo).setContentTitle(title).setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(applicationContext).notify(1,notificationBuilder.build())
    }
}