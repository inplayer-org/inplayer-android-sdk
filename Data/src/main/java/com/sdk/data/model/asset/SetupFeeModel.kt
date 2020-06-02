package com.sdk.data.model.asset

import com.sdk.domain.entity.asset.SetupFeeEntity


data class SetupFeeModel(
    val feeAmount: Int,
    val description: String?
) {
    
    fun mapToEntity(): SetupFeeEntity {
        return SetupFeeEntity(feeAmount = feeAmount, description = description ?: "")
    }
}