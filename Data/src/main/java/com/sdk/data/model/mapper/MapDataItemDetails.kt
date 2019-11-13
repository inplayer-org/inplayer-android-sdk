package com.sdk.data.model.mapper

import com.sdk.data.model.asset.ItemDetailsModel
import com.sdk.data.model.asset.ItemMetadataModel
import com.sdk.domain.entity.asset.ItemDetailsEntity
import com.sdk.domain.entity.asset.ItemMetadataEntity


class MapDataItemDetails constructor(
        private val mapDataAccessControlType: MapDataAccessControlType,
        private val mapItemType: MapDataItemType,
        private val mapDataAccessFee: MapDataAccessFee) : ModelMapper<ItemDetailsModel, ItemDetailsEntity> {
    
    override fun mapFromModel(model: ItemDetailsModel): ItemDetailsEntity {
        return ItemDetailsEntity(id = model.id,
                merchantId = model.merchantId,
                merchantUUID = model.merchantUUID,
                isActive = model.isActive,
                title = model.title,
                accessControlType = mapDataAccessControlType.mapFromModel(model.accessControlTypeModel),
                itemType = mapItemType.mapFromModel(model.itemTypeModel),
                metahash = model.metahash,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt,
                metadata = model.metadata.map { mapItemMetadata(it) },
                accessFees = model.accessFees.map { mapDataAccessFee.mapFromModel(it) },
                content = model.content)
    }
    
    private fun mapItemMetadata(itemMetadata: ItemMetadataModel): ItemMetadataEntity {
        return ItemMetadataEntity(
                id = itemMetadata.id,
                name = itemMetadata.name,
                value = itemMetadata.value)
    }
    
    override fun mapToModel(entity: ItemDetailsEntity): ItemDetailsModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}
