package com.s.data.model.mapper

import com.s.domain.entity.asset.AccessTypeEntity
import com.s.domain.entity.asset.AccessTypeModel

/**
 * Created by victor on 1/6/19
 */
class MapAccessType : ModelMapper<AccessTypeModel, AccessTypeEntity> {
    
    override fun mapFromModel(model: AccessTypeModel): AccessTypeEntity {
        return AccessTypeEntity(id = model.id, quantity = model.quantity, period = model.period, name = model.name)
    }
    
    override fun mapToModel(entity: AccessTypeEntity): AccessTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}