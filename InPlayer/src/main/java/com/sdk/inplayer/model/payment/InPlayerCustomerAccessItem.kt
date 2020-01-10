package com.sdk.inplayer.model.payment

/**
 * Created by victor on 3/15/19
 */
data class InPlayerCustomerAccessItem(
        val consumer_email: String,
        val created_at: Int,
        val customer_id: Int,
        val expires_at: Long,
        val is_trial: Boolean,
        val item_access_id: Int,
        val item_id: Int,
        val item_title: String,
        val merchant_id: Int,
        val parent_resource_id: String,
        val payment_method: String,
        val payment_tool: String,
        val purchased_access_fee_description: String,
        val purchased_access_fee_id: Int,
        val purchased_access_fee_type: String,
        val purchased_amount: Double,
        val purchased_currency: String,
        val revoked: Int,
        val starts_at: Long,
        val type: String
)