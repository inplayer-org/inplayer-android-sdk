package com.sdk.data.model.asset

data class GeoRestrictionApiModel(
    val id: Long,
    val country_iso: String?,
    val country_set_id: Long,
    val type: String?
)