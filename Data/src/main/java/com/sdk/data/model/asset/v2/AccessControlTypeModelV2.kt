package com.sdk.data.model.asset.v2

/**
 * Same class as [AccessControlTypeModel] but auth field is int instead of Boolean
 * */
data class AccessControlTypeModelV2(val id: Long, val name: String, val auth: Int)