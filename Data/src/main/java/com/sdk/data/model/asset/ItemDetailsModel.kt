package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName
import com.sdk.domain.entity.asset.ItemDetailsEntity

data class ItemDetailsModel(
    @SerializedName("id") val id: Long,
    @SerializedName("merchant_id") val merchantId: Long,
    @SerializedName("merchant_uuid") val merchantUUID: String?,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("active") val active: Boolean,
    @SerializedName("title") val title: String?,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("updated_at") val updatedAt: Long,
    @SerializedName("access_fees") val accessFees: List<AccessFeeModel>?,
    @SerializedName("access_control_type") val accessControlTypeModel: AccessControlTypeModel?,
    @SerializedName("item_type") val itemTypeModel: ItemTypeModel?,
    @SerializedName("content") val content: String?,
    @SerializedName("metahash") val metahash: Map<String, String>?,
    @SerializedName("metadata") val metadata: List<ItemMetadataModel>?
){
    fun mapToEntity() : ItemDetailsEntity {
        return ItemDetailsEntity(id = id,
            merchantId = merchantId,
            merchantUUID = merchantUUID ?: "",
            isActive = isActive,
            title = title ?: "",
            accessControlType = accessControlTypeModel?.mapToEntity(),
            itemType = itemTypeModel?.mapToEntity(),
            metahash = metahash ?: hashMapOf(),
            createdAt = createdAt,
            updatedAt = updatedAt,
            metadata = metadata?.map { it.mapToEntity() } ?: listOf(),
            accessFees = accessFees?.map { it.mapToEntity() } ?: listOf(),
            content = content)
    }
}
