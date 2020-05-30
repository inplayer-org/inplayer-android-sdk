package com.sdk.data.model.subscription

/**
 * Created by victor on 3/16/19
 */
data class SubscriptionModel(
        val amount: Int,
        val asset_id: Int,
        val asset_title: String,
        val cancel_token: String,
        val created_at: Long,
        val currency: String,
        val description: String,
        val formatted_amount: String,
        val merchant_id: Int,
        val next_billing_date: Long,
        val status: String,
        val unsubscribe_url: String,
        val updated_at: Long
)
