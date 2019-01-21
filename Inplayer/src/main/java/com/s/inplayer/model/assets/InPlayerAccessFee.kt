package com.s.inplayer.model.assets

/**
 * Created by victor on 1/5/19
 */
data class InPlayerAccessFee(val id: Long,
                             val merchantId: Long,
                             val amount: Long,
                             val currency: String,
                             val description: String,
                             val accessType: InPlayerAccessType,
                             val itemType: String,
                             val trialPeriod: InPlayerTrialPeriod?,
                             val setupFee: InPlayerSetupFee?,
                             val expiresAt: Long?)