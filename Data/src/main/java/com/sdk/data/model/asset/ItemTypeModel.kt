package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName
import com.sdk.domain.entity.asset.ItemTypeEntity


data class ItemTypeModel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("content_type") val contentType: String?,
    @SerializedName("host") val host: String?,
    @SerializedName("description") val description: String?
) {
    fun mapToEntity(): ItemTypeEntity {
        return ItemTypeEntity(
            id = id,
            name = name ?: "",
            contentType = contentType ?: "",
            host = host ?: "",
            description = description ?: ""
        )
    }
}