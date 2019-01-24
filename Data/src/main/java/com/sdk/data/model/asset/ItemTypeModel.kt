package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName


data class ItemTypeModel(val id: Long, val name: String, @SerializedName("content_type") val contentType: String, val host: String, val description: String)