package com.s.inplayer.model.assets

/**
 * Created by victor on 1/5/19
 */
data class AccessFee(val id: Long, val merchantId: Long, val amount: Long, val currency: String, val description: String,
                     val accessTypeEntity: AccessType, val itemType: String, val trialPeriodEntity: TrialPeriod?,
                     val setupFeeEntity: SetupFee?,
                     val expiresAt: Long?)