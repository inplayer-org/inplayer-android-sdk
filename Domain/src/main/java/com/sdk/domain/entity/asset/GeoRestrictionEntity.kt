package com.sdk.domain.entity.asset

data class GeoRestrictionEntity(
    val id: Long,
    val country_iso: String?,
    val country_set_id: Long,
    val type: String?
)