package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity


data class InPlayerSubscribeSuccessNotification(val resource: InPlayerSubscribeSuccessNotificationResource,
                                                override val type: String, override val timestamp: Long) : InPlayerNotificationEntity

data class InPlayerSubscribeSuccessNotificationResource(
        val access_fee_id: Int,
        val amount: String?,
        val code: Int,
        val currency_iso: String?,
        val customer_id: Int,
        val description: String?,
        val email: String?,
        val customer_email: String?,
        val discount_percent: Int,
        val formatted_amount: String?,
        val full_amount: String?,
        val item_id: Int,
        val payment_method: String?,
        val preview_Title: String?,
        val status: String?,
        val voucher_code: String?,
        val timestamp: Int,
        val transaction: String?
)
