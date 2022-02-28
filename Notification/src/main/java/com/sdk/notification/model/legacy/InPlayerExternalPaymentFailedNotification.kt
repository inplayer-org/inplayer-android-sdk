package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity


data class InPlayerExternalPaymentFailedNotification(
    val resource: InPlayerExternalPaymentFailedNotificationResource,
    override val type: String, override val timestamp: Long
) : InPlayerNotificationEntity

data class InPlayerExternalPaymentFailedNotificationResource(
    val code: Int,
    val explain: String?,
    val message: String?
)