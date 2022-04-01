package com.sdk.notification.model.legacy

import com.sdk.notification.model.notification.InPlayerNotificationEntity

data class InPlayerExternalSubscriberCancelNotification(val resource: InPlayerExternalSubscriberCancelNotificationResource,
                                                 override val type: String, override val timestamp: Long) : InPlayerNotificationEntity

data class InPlayerExternalSubscriberCancelNotificationResource(
        val access_fee_id: Int,
        val amount: String?,
        val code: Int,
        val currency_iso: String?,
        val customer_id: Int,
        val description: String?,
        val discount_percent: Int,
        val email: String?,
        val formatted_amount: String?,
        val full_amount: String?,
        val item_id: Int,
        val next_rebill_date: Long,
        val payment_method: String?,
        val previewTitle: String?,
        val status: String?,
        val transaction: String?,
        val voucher_code: String?,
        val timestamp: Long
)