package com.sdk.domain.entity.payment

data class CustomerAccessItemEntity(
        val consumer_email: String,
        val created_at: Int,
        val customer_id: Int,
        val expires_at: Long,
        val is_trial: Boolean,
        val item_access_id: Int,
        val item_id: Int,
        val item_title: String,
        val merchant_id: Int,
        val parent_resource_id: String ?= "",  // can be set null
        val payment_method: String?= "",  // can be set null
        val payment_tool: String?= "",  // can be set null
        val purchased_access_fee_description: String?= "",  // can be set null
        val purchased_access_fee_id: Int?= 0,  // can be set null
        val purchased_access_fee_type: String?= "",  // can be set null
        val purchased_amount: Double?= 0.0,  // can be set null
        val purchased_currency: String?= "",  // can be set null
        val revoked: Int,
        val starts_at: Long,
        val type: String
)