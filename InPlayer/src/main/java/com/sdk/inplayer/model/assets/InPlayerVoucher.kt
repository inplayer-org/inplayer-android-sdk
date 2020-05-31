package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.VoucherEntity

data class InPlayerVoucher(
    val code: String?,
    val discount: Int,
    val discount_duration: Int,
    val discount_period: String?,
    val end_date: String?,
    val id: Int,
    val name: String?,
    val rebill_discount: Int,
    val start_date: String?,
    val usage_counter: Int,
    val usage_limit: Int
) {
    constructor(voucherEntity: VoucherEntity) : this(
        voucherEntity.code,
        voucherEntity.discount,
        voucherEntity.discount_duration,
        voucherEntity.discount_period,
        voucherEntity.end_date,
        voucherEntity.id,
        voucherEntity.name,
        voucherEntity.rebill_discount,
        voucherEntity.start_date,
        voucherEntity.usage_counter,
        voucherEntity.usage_limit
    )
}
