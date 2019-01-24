package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName


data class AccessFeeModel(val id: Long, @SerializedName("merchant_id") val merchantId: Long, val amount: Long,
                          val currency: String, val description: String, @SerializedName("access_type") val accessTypeModel: AccessTypeModel,
                          @SerializedName("item_type") val itemType: String, val trialPeriodModel: TrialPeriodModel?,
                          val setupFeeModel: SetupFeeModel?, val expiresAt: Long?)