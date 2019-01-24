package com.sdk.inplayer.callback

import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus


interface InPlayerNotificationCallback {
    
    fun onStatusChanged(status: InPlayerNotificationStatus)
    
    fun onMessageReceived(message: InPlayerNotification)
    
    fun onError(t: InPlayerException)
    
}