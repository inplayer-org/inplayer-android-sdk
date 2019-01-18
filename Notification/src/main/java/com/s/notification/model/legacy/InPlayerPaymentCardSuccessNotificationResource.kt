package com.s.notification.model.legacy

import com.s.notification.model.notification.InPlayerNotification

data class InPlayerPaymentCardSuccessNotifcation(val resource: InPlayerPaymentCardSuccessNotificationResource,
                                                 override val type: String, override val timestamp: Long) : InPlayerNotification

data class InPlayerPaymentCardSuccessNotificationResource(
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