package com.s.notification.model.legacy

import com.s.notification.model.notification.InPlayerNotification

/**
 * Created by victor on 1/16/19
 */
data class InPlayerSubscribeSuccessNotification(val resource: InPlayerSubscribeSuccessNotificationResource,
                                                override val type: String, override val timestamp: Long) : InPlayerNotification

data class InPlayerSubscribeSuccessNotificationResource(
        val access_fee_id: Int,
        val amount: String,
        val code: Int,
        val currency_iso: String,
        val customer_id: Int,
        val description: String,
        val email: String,
        val formatted_amount: String,
        val status: String,
        val timestamp: Int,
        val transaction: String
)