package com.s.data.model.mapper

import com.s.data.model.asset.AccessControlTypeModel
import com.s.domain.entity.asset.AccessControlTypeEntity

/**
 * Created by victor on 1/6/19
 */
class MapDataAccessControlType : ModelMapper<AccessControlTypeModel, AccessControlTypeEntity> {
    
    override fun mapFromModel(model: AccessControlTypeModel): AccessControlTypeEntity {
        return AccessControlTypeEntity(id = model.id, name = model.name, auth = model.auth)
    }
    
    override fun mapToModel(entity: AccessControlTypeEntity): AccessControlTypeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}