package com.s.inplayer.model.notification

/**
 * Created by victor on 1/16/19
 */
data class INPAccessRevokedNotification(val resource: INPAccessRevokedNotificationResource,
                                        override val type: String, override val timestamp: Long) : InPlayerNotification

data class INPAccessRevokedNotificationResource(val item_id: Int)