package com.sdk.data.model.mapper

import com.sdk.data.model.asset.ItemTypeModel
import com.sdk.domain.entity.asset.ItemTypeEntity


class MapDataItemType : ModelMapper<ItemTypeModel, ItemTypeEntity> {
    
    override fun mapFromModel(model: ItemTypeModel): ItemTypeEntity {
        return ItemTypeEntity(id = model.id, name = model.name, contentType = model.contentType, host = model.host, description = model.description)
    }
    
    override fun mapToModel(entity: ItemTypeEntity): ItemTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}