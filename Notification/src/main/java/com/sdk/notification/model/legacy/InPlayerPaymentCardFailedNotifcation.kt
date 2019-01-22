package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity

/**
 * Created by victor on 1/16/19
 */
data class InPlayerPaymentCardFailedNotifcation(val resource: InPlayerPaymentCardFailedNotificationResource,
                                                override val type: String, override val timestamp: Long) : InPlayerNotificationEntity

data class InPlayerPaymentCardFailedNotificationResource(
        val access_fee_id: Int,
        val account_id: Int,
        val code: Int,
        val message: String
)