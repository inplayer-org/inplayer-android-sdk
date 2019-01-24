package com.sdk.inplayer.model.notification


data class InPlayerAccessRevokedNotification(val resource: InPlayerAccessRevokedNotificationResource,
                                             override val type: String, override val timestamp: Long) : InPlayerNotification

data class InPlayerAccessRevokedNotificationResource(val item_id: Int)