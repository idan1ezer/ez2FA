//package com.idan.ez2fa
//
//import android.annotation.SuppressLint
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.os.Build
//import android.os.IBinder
//import androidx.core.app.NotificationCompat
//
//class VolumeKeyService : Service() {
//
//    private var volumeKeyReceiver: VolumeKeyReceiver? = null
//
//    override fun onCreate() {
//        super.onCreate()
//        super.onCreate()
//        volumeKeyReceiver = VolumeKeyReceiver(this)
//        val intentFilter = IntentFilter().apply {
//            addAction("android.media.VOLUME_CHANGED_ACTION")
//        }
//        registerReceiver(volumeKeyReceiver, intentFilter)
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        // Make this service run in the foreground
//        val notification = createNotification()
//        startForeground(1, notification)
//
//        // Return START_STICKY to ensure the service is restarted if it gets terminated
//        return START_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        volumeKeyReceiver?.let {
//            unregisterReceiver(it)
//            volumeKeyReceiver = null
//        }
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//
//    private fun createNotification(): Notification {
//        // Create and return a notification for the foreground service
//        // Customize the notification as per your requirements
//        // Example:
//        val notificationChannelId = "VolumeKeyChannelId"
//        val notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)
//            .setContentTitle("Volume Key Service")
//            .setContentText("Listening for volume key events")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                notificationChannelId,
//                "Volume Key Channel",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        return notificationBuilder.build()
//    }
//}
