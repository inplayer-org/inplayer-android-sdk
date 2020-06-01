package com.sdk.data.model.asset

import com.google.gson.annotations.JsonAdapter
import com.sdk.domain.entity.asset.AccessControlTypeEntity

@JsonAdapter(AccessControlTypeEntity::class)
data class AccessControlTypeModel(val id: Long, val name: String, val auth: Boolean)