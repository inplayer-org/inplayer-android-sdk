package com.s.data.model.mapper

import com.s.domain.entity.asset.ItemDetailsEntity
import com.s.domain.entity.asset.ItemDetailsModel

/**
 * Created by victor on 1/6/19
 */
class MapDataItemDetails constructor(val mapDataAccessControlType: MapDataAccessControlType, val mapItemType: MapDataItemType) : ModelMapper<ItemDetailsModel, ItemDetailsEntity> {
    
    override fun mapFromModel(model: ItemDetailsModel): ItemDetailsEntity {
        return ItemDetailsEntity(id = model.id, merchantId = model.merchantId, merchantUUID = model.merchantUUID, isActive = model.isActive, title = model.title,
                accessControlTypeEntity = mapDataAccessControlType.mapFromModel(model.accessControlTypeModel), itemTypeEntity = mapItemType.mapFromModel(model.itemTypeModel), metaData = model.metahash,
                createdAt = model.createdAt, updatedAt = model.updatedAt)
    }
    
    override fun mapToModel(entity: ItemDetailsEntity): ItemDetailsModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}