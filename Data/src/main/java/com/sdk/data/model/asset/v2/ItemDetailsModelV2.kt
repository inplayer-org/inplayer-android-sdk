package com.sdk.data.model.asset.v2

import com.google.gson.annotations.SerializedName
import com.sdk.domain.entity.asset.ItemAccessEntity

data class ItemDetailsModelV2(
    @SerializedName("id") val id: Long,
    @SerializedName("merchant_id") val merchantId: Long,
    @SerializedName("merchant_uuid") val merchantUUID: String?,
    @SerializedName("access_control_type") val accessControlTypeModel: AccessControlTypeModelV2?,
    @SerializedName("account_id") val accountId: Long,
    @SerializedName("customer_id") val customerId: Long,
    @SerializedName("customer_uuid") val customerUUID: String?,
    @SerializedName("ip_address") val ipAddress: String?,
    @SerializedName("country_code") val countryCode: String?,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("expires_at") val expiresAt: Long,
    @SerializedName("active") val active: Boolean,
    @SerializedName("content") val content: String?,
    @SerializedName("title") val title: String?
) {
    fun mapToEntity(): ItemAccessEntity {
        return ItemAccessEntity(
            id = id,
            merchantId = merchantId,
            merchantUUID = merchantUUID ?: "",
            accessControlTypeEntity = accessControlTypeModel?.mapToEntity(),
            accountId = accountId,
            customerId = customerId,
            customerUUID = customerUUID ?: "",
            ipAddress = ipAddress ?: "",
            countryCode = countryCode ?: "",
            createdAt = createdAt,
            expiresAt = expiresAt,
            itemDetailsEntity = null,
            active = active,
            content = content,
            title = title
        )
    }
}