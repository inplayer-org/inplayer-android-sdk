package com.sdk.domain.entity.asset


data class AccessFeeEntity(val id: Long, val merchantId: Long, val amount: Float, val currency: String, val description: String,
                           val accessTypeEntity: AccessTypeEntity, val itemType: String, val trialPeriodEntity: TrialPeriodEntity?, val setupFeeEntity: SetupFeeEntity?,
                           val expiresAt: Long?)