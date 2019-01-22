package com.sdk.inplayer.callback

import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus

/**
 * Created by victor on 1/16/19
 */
interface InPlayerNotificationCallback {
    
    fun onStatusChanged(status: InPlayerNotificationStatus)
    
    fun onMessageReceived(message: InPlayerNotification)
    
    fun onError(t: InPlayerException)
    
}