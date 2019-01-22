package com.s.data.model.mapper

import com.s.data.model.asset.ItemTypeModel
import com.s.domain.entity.asset.ItemTypeEntity

/**
 * Created by victor on 1/6/19
 */
class MapDataItemType : ModelMapper<ItemTypeModel, ItemTypeEntity> {
    
    override fun mapFromModel(model: ItemTypeModel): ItemTypeEntity {
        return ItemTypeEntity(id = model.id, name = model.name, contentType = model.contentType, host = model.host, description = model.description)
    }
    
    override fun mapToModel(entity: ItemTypeEntity): ItemTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}