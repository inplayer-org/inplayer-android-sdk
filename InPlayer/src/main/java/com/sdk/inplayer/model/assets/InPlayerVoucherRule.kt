package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.VoucherRuleEntity

data class InPlayerVoucherRule(
    val id: Int,
    val rule_type: String?,
    val value: Int,
    val voucher: InPlayerVoucher?
) {
    constructor(voucherRuleEntity: VoucherRuleEntity) : this(
        voucherRuleEntity.id,
        voucherRuleEntity.rule_type,
        voucherRuleEntity.value,
        voucherRuleEntity.voucher?.let { InPlayerVoucher(it) }
    )
}
