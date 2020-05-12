package com.sdk.data.model.mapper

import com.sdk.data.model.asset.AccessTypeModel
import com.sdk.domain.entity.asset.AccessTypeEntity


class MapDataAccessType : ModelMapper<AccessTypeModel, AccessTypeEntity> {
    
    override fun mapFromModel(model: AccessTypeModel): AccessTypeEntity {
        return AccessTypeEntity(
            id = model.id,
            quantity = model.quantity,
            period = model.period ?: "",
            name = model.name ?: ""
        )
    }
    
    override fun mapToModel(entity: AccessTypeEntity): AccessTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}