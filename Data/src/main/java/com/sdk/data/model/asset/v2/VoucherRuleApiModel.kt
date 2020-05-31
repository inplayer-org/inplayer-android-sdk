package com.sdk.data.model.asset.v2

import com.sdk.domain.entity.asset.VoucherRuleEntity

data class VoucherRuleApiModel(
    val id: Int,
    val rule_type: String?,
    val value: Int,
    val voucher: VoucherApiModel?
) {
    fun mapToEntity(): VoucherRuleEntity {
        return VoucherRuleEntity(id, rule_type, value, voucher?.mapToEntity())
    }
}
