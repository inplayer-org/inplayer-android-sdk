package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.GeoRestrictionEntity

data class InPlayerGeoRestriction(
    val id: Long,
    val country_iso: String?,
    val country_set_id: Long,
    val type: String?
) {
    constructor(geoRestrictionEntity: GeoRestrictionEntity) : this(
        id = geoRestrictionEntity.id,
        country_iso = geoRestrictionEntity.country_iso,
        country_set_id = geoRestrictionEntity.country_set_id,
        type = geoRestrictionEntity.type
    )
}
