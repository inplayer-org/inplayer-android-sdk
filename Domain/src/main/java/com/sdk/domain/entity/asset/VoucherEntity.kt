package com.sdk.domain.entity.asset

data class VoucherEntity(
    val code: String?,
    val discount: Int,
    val discount_duration: Int,
    val discount_period: String?,
    val end_date: Long,
    val id: Int,
    val name: String?,
    val rebill_discount: Int,
    val start_date: Long,
    val usage_counter: Int,
    val usage_limit: Int
)