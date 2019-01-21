package com.s.data.model.asset

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 1/5/19
 */
data class ItemTypeModel(val id: Long, val name: String, @SerializedName("content_type") val contentType: String, val host: String, val description: String)