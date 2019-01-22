package com.sdk.data.model.account

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 12/25/18
 */
data class InPlayerAccount(val id: Long,
                           val email: String,
                           @SerializedName("full_name") val fullName: String,
                           val referrer: String,
                           val roles: List<String>,
                           @SerializedName("completed") val isCompleted: Boolean,
                           @SerializedName("created_at") val createdAt: Long,
                           @SerializedName("updated_at") val updatedAt: Long,
                           @SerializedName("merchant_id") val merchantId: String,
                           @SerializedName("merchant_uuid") val merchantUUID: String,
                           val metadata: HashMap<String, String>,
                           val uuid: String,
                           val username: String
)
