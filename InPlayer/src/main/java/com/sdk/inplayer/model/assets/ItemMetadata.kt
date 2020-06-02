package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.ItemMetadataEntity

class ItemMetadata(
    val id: Int,
    val name: String,
    val value: String
) {
    constructor(itemMetadata: ItemMetadataEntity) : this(
        id = itemMetadata.id,
        name = itemMetadata.name,
        value = itemMetadata.value
    )
}
