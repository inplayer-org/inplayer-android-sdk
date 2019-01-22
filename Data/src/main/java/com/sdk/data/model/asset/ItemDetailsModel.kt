package com.sdk.data.model.asset

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 1/5/19
 */
data class ItemDetailsModel(val id: Long,
                            @SerializedName("merchant_id") val merchantId: Long,
                            @SerializedName("merchant_uuid") val merchantUUID: String,
                            @SerializedName("is_active") val isActive: Boolean,
                            val title: String,
                            @SerializedName("created_at") val createdAt: Long,
                            @SerializedName("updated_at") val updatedAt: Long,
                            @SerializedName("access_fees") val accessFees: List<AccessFeeModel>,
                            @SerializedName("access_control_type") val accessControlTypeModel: AccessControlTypeModel,
                            @SerializedName("item_type") val itemTypeModel: ItemTypeModel,
                            val metahash: Map<String, String>,
                            val metadata: List<ItemMetadataModel>)
