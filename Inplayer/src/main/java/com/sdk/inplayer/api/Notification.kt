package com.sdk.inplayer.api

import com.sdk.inplayer.callback.InPlayerNotificationCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.mapper.notification.NotificationMapper
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus
import com.sdk.notification.AWSNotificationCallback
import com.sdk.notification.AWSNotificationManager
import com.sdk.notification.model.notification.InPlayerNotificationEntity

/**
 * Created by victor on 1/16/19
 */
class Notification internal constructor(private val notificationManager: AWSNotificationManager,
                               private val notificationMapper: NotificationMapper) {
    
    fun subscribe(callback: InPlayerNotificationCallback) {
        notificationManager.subscribe(object : AWSNotificationCallback {
            
            override fun onMessageReceived(message: InPlayerNotificationEntity) {
                callback.onMessageReceived(notificationMapper.mapFromDomain(message))
            }
            
            override fun onStatusChanged(status: String) {
                callback.onStatusChanged(InPlayerNotificationStatus.valueOf(status.toLowerCase().capitalize()))
            }
            
            override fun onError(t: Throwable) {
                callback.onError(ThrowableToInPlayerExceptionMapper.mapThrowableToException(t))
            }
        })
    }
    
    fun disconnect() {
        notificationManager.discconnect()
    }
    
}