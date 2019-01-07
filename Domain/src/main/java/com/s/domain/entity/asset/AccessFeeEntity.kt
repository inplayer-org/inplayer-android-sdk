package com.s.domain.entity.asset

/**
 * Created by victor on 1/5/19
 */
data class AccessFeeEntity(val id: Long, val merchantId: Long, val amount: Long, val currency: String, val description: String,
                           val accessTypeEntity: AccessTypeEntity, val itemType: String, val trialPeriodEntity: TrialPeriodEntity?, val setupFeeEntity: SetupFeeEntity?,
                           val expiresAt: Long?)