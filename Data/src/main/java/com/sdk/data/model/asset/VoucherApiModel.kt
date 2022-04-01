package com.sdk.data.model.asset

import com.sdk.domain.entity.asset.VoucherEntity

data class VoucherApiModel(
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
) {
    fun mapToEntity(): VoucherEntity {
        return VoucherEntity(
            code,
            discount,
            discount_duration,
            discount_period,
            end_date,
            id,
            name,
            rebill_discount,
            start_date,
            usage_counter,
            usage_limit
        )
    }
}