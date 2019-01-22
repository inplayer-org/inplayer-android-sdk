package com.sdk.inplayer.model.notification


data class INPAccessRevokedNotification(val resource: INPAccessRevokedNotificationResource,
                                        override val type: String, override val timestamp: Long) : InPlayerNotification

data class INPAccessRevokedNotificationResource(val item_id: Int)