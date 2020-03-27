package com.sdk.data.model.account

import com.google.gson.annotations.SerializedName

data class InPlayerAccount(@SerializedName("id") val id: Long,
                           @SerializedName("email")  val email: String,
                           @SerializedName("full_name") val fullName: String,
                           @SerializedName("referrer") val referrer: String,
                           @SerializedName("roles") val roles: List<String>,
                           @SerializedName("completed") val isCompleted: Boolean,
                           @SerializedName("created_at") val createdAt: Long,
                           @SerializedName("updated_at") val updatedAt: Long,
                           @SerializedName("merchant_id") val merchantId: String,
                           @SerializedName("merchant_uuid") val merchantUUID: String,
                           @SerializedName("metadata") val metadata: HashMap<String, String>,
                           @SerializedName("uuid") val uuid: String,
                           @SerializedName("username") val username: String
)

