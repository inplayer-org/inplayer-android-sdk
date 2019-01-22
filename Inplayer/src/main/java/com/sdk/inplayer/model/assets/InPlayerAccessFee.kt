package com.sdk.inplayer.model.assets


class InPlayerAccessFee(val id: Long,
                             val merchantId: Long,
                             val amount: Long,
                             val currency: String,
                             val description: String,
                             val accessType: InPlayerAccessType,
                             val itemType: String,
                             val trialPeriod: InPlayerTrialPeriod?,
                             val setupFee: InPlayerSetupFee?,
                             val expiresAt: Long?)