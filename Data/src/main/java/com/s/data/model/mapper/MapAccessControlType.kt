package com.s.data.model.mapper

import com.s.domain.entity.asset.AccessControlTypeEntity
import com.s.domain.entity.asset.AccessControlTypeModel

/**
 * Created by victor on 1/6/19
 */
class MapAccessControlType : ModelMapper<AccessControlTypeModel, AccessControlTypeEntity> {
    
    override fun mapFromModel(model: AccessControlTypeModel): AccessControlTypeEntity {
        return AccessControlTypeEntity(id = model.id, name = model.name, auth = model.auth)
    }
    
    override fun mapToModel(entity: AccessControlTypeEntity): AccessControlTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}