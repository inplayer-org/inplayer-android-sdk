package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName


data class ItemTypeModel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("content_type") val contentType: String?,
    @SerializedName("host") val host: String?,
    @SerializedName("description") val description: String?
)