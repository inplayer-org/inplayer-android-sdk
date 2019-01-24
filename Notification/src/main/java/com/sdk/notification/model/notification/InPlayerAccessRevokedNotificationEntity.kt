package com.sdk.notification.model.notification


data class InPlayerAccessRevokedNotification(val resource: InPlayerAccessRevokedNotificationResource,
                                             override val type: String, override val timestamp: Long) : InPlayerNotificationEntity

data class InPlayerAccessRevokedNotificationResource(val item_id: Int)