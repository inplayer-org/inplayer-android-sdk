package com.sdk.data.model.asset

import com.sdk.domain.entity.asset.ItemMetadataEntity

data class ItemMetadataModel(val id: Int, val name: String, val value: String) {
    fun mapToEntity(): ItemMetadataEntity {
        return ItemMetadataEntity(
            id = id,
            name = name,
            value = value
        )
    }
}
