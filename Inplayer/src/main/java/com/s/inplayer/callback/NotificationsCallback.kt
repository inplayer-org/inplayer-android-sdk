package com.s.inplayer.callback

import com.s.inplayer.callback.error.InPlayerException
import com.s.inplayer.model.notification.INPNotification

/**
 * Created by victor on 1/16/19
 */
interface NotificationsCallback {
    
    fun onStatusChanged(status: String)
    
    fun onMessageReceived(message: INPNotification)
    
    fun onError(t: InPlayerException)
    
}