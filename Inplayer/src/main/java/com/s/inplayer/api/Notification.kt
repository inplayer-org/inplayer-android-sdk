package com.s.inplayer.api

import com.s.inplayer.callback.NotificationsCallback
import com.s.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.s.inplayer.mapper.notification.NotificationMapper
import com.s.notification.AWSNotificationCallback
import com.s.notification.AWSNotificationManager
import com.s.notification.model.notification.InPlayerNotification

/**
 * Created by victor on 1/16/19
 */
class Notification constructor(private val notificationManager: AWSNotificationManager,
                               private val notificationMapper: NotificationMapper) {
    
    fun subScribe(callback: NotificationsCallback) {
        notificationManager.subscribe(object : AWSNotificationCallback {
            
            override fun onMessageReceived(message: InPlayerNotification) {
                callback.onMessageReceived(notificationMapper.mapFromDomain(message))
            }
            
            override fun onStatusChanged(status: String) {
                callback.onStatusChanged(status)
            }
            
            override fun onError(t: Throwable) {
                callback.onError(ThrowableToInPlayerExceptionMapper.mapThrowableToException(t))
            }
        })
    }
}