package com.moengage.newsapp.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.newsapp.utils.NotificationUtils

class PushNotificationServices : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.data.isNotEmpty().let {
            // Handle data payload here
            // You can customize how you want to handle the data
            // For example, create and show a notification
            val notificationData = message.data
            val title = notificationData["title"]
            val body = notificationData["body"]
            // Display notification
            // NotificationUtils is a custom class to handle notifications
            NotificationUtils.displayNotification(applicationContext, title, body)
        }
    }
}