package com.sdk.data.model.mapper

import com.sdk.data.model.asset.AccessControlTypeModel
import com.sdk.domain.entity.asset.AccessControlTypeEntity


class MapDataAccessControlType : ModelMapper<AccessControlTypeModel, AccessControlTypeEntity> {
    
    override fun mapFromModel(model: AccessControlTypeModel): AccessControlTypeEntity {
        return AccessControlTypeEntity(id = model.id, name = model.name, auth = false)
    }
    
    override fun mapToModel(entity: AccessControlTypeEntity): AccessControlTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}