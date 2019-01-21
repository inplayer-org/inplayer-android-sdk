package com.s.inplayer.callback

import com.s.inplayer.model.error.InPlayerException
import com.s.inplayer.model.notification.InPlayerNotification
import com.s.inplayer.model.notification.InPlayerNotificationStatus

/**
 * Created by victor on 1/16/19
 */
interface NotificationsCallback {
    
    fun onStatusChanged(status: InPlayerNotificationStatus)
    
    fun onMessageReceived(message: InPlayerNotification)
    
    fun onError(t: InPlayerException)
    
}