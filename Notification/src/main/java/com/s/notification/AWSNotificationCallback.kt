package com.s.notification

import com.s.notification.model.notification.InPlayerNotification

/**
 * Created by victor on 1/16/19
 */
interface AWSNotificationCallback {
    
    fun onStatusChanged(status: String)
    
    fun onMessageReceived(message: InPlayerNotification)
    
    fun onError(t: Throwable)
    
}

