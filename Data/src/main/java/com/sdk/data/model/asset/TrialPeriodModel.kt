package com.sdk.data.model.asset

import com.sdk.domain.entity.asset.TrialPeriodEntity


data class TrialPeriodModel(
    val quantity: Int,
    val period: String?,
    val description: String?
) {
    fun mapToEntity(): TrialPeriodEntity {
        return TrialPeriodEntity(
            quantity = quantity,
            period = period ?: "",
            description = description ?: ""
        )
    }
}