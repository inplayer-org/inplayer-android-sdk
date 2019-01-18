package com.s.notification.model.notification

/**
 * Created by victor on 1/16/19
 */
data class InPlayerAccessRevokedNotification(val resource: InPlayerAccessRevokedNotificationResource,
                                             override val type: String, override val timestamp: Long) : InPlayerNotification

data class InPlayerAccessRevokedNotificationResource(val item_id: Int)