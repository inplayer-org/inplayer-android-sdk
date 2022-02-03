package com.sdk.inplayer.api

import android.util.Log
import com.sdk.inplayer.callback.InPlayerNotificationCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.mapper.notification.NotificationMapper
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus
import com.sdk.notification.AWSNotificationCallback
import com.sdk.notification.AWSNotificationManager
import com.sdk.notification.model.notification.InPlayerNotificationEntity
import java.util.*


class Notification internal constructor(private val notificationManager: AWSNotificationManager,
                                        private val notificationMapper: NotificationMapper) {
    
    /**
     * Subscribes the user to listen for Notifications
     *
     * @param callback InPlayerNotificationCallback
     */
    fun subscribe(callback: InPlayerNotificationCallback) {
        notificationManager.subscribe(object : AWSNotificationCallback {
            
            override fun onMessageReceived(message: InPlayerNotificationEntity) {
                Log.i("Notif.onMSGReceived 1", "InPlayerNotificationEntity --> $message")
                callback.onMessageReceived(notificationMapper.mapFromDomain(message))
            }
            
            override fun onStatusChanged(status: String) {
                callback.onStatusChanged(InPlayerNotificationStatus.valueOf(
                    status.lowercase(Locale.getDefault())
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                )
            }
            
            override fun onError(t: Throwable) {
                callback.onError(ThrowableToInPlayerExceptionMapper.mapThrowableToException(t))
            }
        })
    }
    
    /**
     * Closes the connection for notifications
     */
    fun unsubscribe() {
        notificationManager.disconnect()
    }
    
    fun isSubscribed() = notificationManager.isSubscribed
    
}