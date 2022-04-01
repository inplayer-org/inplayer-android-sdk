package com.sdk.data.model.subscription

/**
 * Created by victor on 3/16/19
 */
data class SubscriptionModel(
        val amount: Int,
        val item_id: Int,
        val item_title: String?,
        val cancel_token: String?,
        val action_type: String?,
        val created_at: Long,
        val currency: String?,
        val description: String?,
        val formatted_amount: String?,
        val merchant_id: Int,
        val next_rebill_date: Long,
        val status: String?,
        val unsubscribe_url: String?,
        val updated_at: Long
)
