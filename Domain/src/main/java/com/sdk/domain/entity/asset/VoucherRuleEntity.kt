package com.sdk.domain.entity.asset

data class VoucherRuleEntity(
    val id: Int,
    val rule_type: String?,
    val value: Int,
    val voucher: VoucherEntity?
)