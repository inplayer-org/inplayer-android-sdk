package com.s.domain.entity.asset

/**
 * Created by victor on 1/5/19
 */
data class AccessFee(val id: Long, val merchantId: Long, val amount: Long, val currency: String, val description: String,
                     val accessTypeEntity: AccessType, val itemTypeEntity: ItemType, val trialPeriodEntity: TrialPeriod, val setupFeeEntity: SetupFee,
                     val expiresAt: Long)