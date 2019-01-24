package com.sdk.notification

import com.sdk.notification.model.notification.InPlayerNotificationEntity

interface AWSNotificationCallback {
    
    fun onStatusChanged(status: String)
    
    fun onMessageReceived(message: InPlayerNotificationEntity)
    
    fun onError(t: Throwable)
    
}

