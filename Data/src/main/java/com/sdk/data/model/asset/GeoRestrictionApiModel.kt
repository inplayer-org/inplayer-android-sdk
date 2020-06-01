package com.sdk.data.model.asset

import com.sdk.domain.entity.asset.GeoRestrictionEntity

data class GeoRestrictionApiModel(
    val id: Long,
    val country_iso: String?,
    val country_set_id: Long,
    val type: String?
) {
    fun mapToEntity() = GeoRestrictionEntity(
        id = id,
        country_iso = country_iso,
        country_set_id = country_set_id,
        type = type
    )
}