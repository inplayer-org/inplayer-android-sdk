package com.s.domain.entity.asset

/**
 * Created by victor on 1/5/19
 */
data class AccessFeeModel(val id: Long, val merchantId: Long, val amount: Long, val currency: String, val description: String,
                          val accessTypeModel: AccessTypeModel, val itemTypeModel: ItemTypeModel, val trialPeriodModel: TrialPeriodModel, val setupFeeModel: SetupFeeModel,
                          val expiresAt: Long)