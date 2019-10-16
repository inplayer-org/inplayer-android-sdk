package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName

data class ItemAccessModel(val id: Long,
                           @SerializedName("account_id") val accountId: Long,
                           @SerializedName("customer_id") val customerId: Long,
                           @SerializedName("customer_uuid") val customerUUID: String,
                           @SerializedName("ip_address") val ipAddress: String,
                           @SerializedName("country_code") val countryCode: String,
                           @SerializedName("created_at") val createdAt: Long,
                           @SerializedName("expires_at") val expiresAt: Long,
                           @SerializedName("item") val itemDetailsModel: ItemDetailsModel)