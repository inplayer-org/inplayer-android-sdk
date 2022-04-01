package com.sdk.inplayer.model.assets

import com.sdk.domain.entity.asset.ItemTypeEntity


class InPlayerItemType(
    val id: Long,
    val name: String,
    val contentType: String,
    val host: String,
    val description: String
) {
    
    constructor(itemTypeEntity: ItemTypeEntity) : this(
        itemTypeEntity.id,
        itemTypeEntity.name,
        itemTypeEntity.contentType,
        itemTypeEntity.host,
        itemTypeEntity.description
    )
}