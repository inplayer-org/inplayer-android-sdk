package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity


data class InPlayerSubscribeCancelledSuccessNotification(
    val resource: InPlayerSubscribeCancelledSuccessNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotificationEntity

data class InPlayerSubscribeCancelledSuccessNotificationResource(
    val subscription_id: String?,
    val message: String?
)