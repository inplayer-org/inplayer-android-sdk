package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity


data class InPlayerPaymentCardFailedNotification(val resource: InPlayerPaymentCardFailedNotificationResource,
                                                 override val type: String, override val timestamp: Long) : InPlayerNotificationEntity

data class InPlayerPaymentCardFailedNotificationResource(
        val access_fee_id: Int,
        val account_id: Int,
        val code: Int,
        val message: String
)