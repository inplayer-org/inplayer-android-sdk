package com.s.inplayer.model.assets

/**
 * Created by victor on 1/5/19
 */
data class InPlayerAccessFee(val id: Long, val merchantId: Long, val amount: Long, val currency: String, val description: String,
                             val inPlayerAccessTypeEntity: InPlayerAccessType, val itemType: String, val inPlayerTrialPeriodEntity: InPlayerTrialPeriod?,
                             val inPlayerSetupFeeEntity: InPlayerSetupFee?,
                             val expiresAt: Long?)