package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.SetupFeeEntity


class InPlayerSetupFee(val feeAmount: Int, val description: String) {
    constructor(setupFeeEntity: SetupFeeEntity) : this(
        setupFeeEntity.feeAmount, setupFeeEntity.description
    )
}