package com.sdk.data.model.mapper.account

import com.sdk.data.model.account.InPlayerRegisterFieldsModel
import com.sdk.data.model.mapper.ModelMapper
import com.sdk.domain.entity.account.RegisterFieldTypeEntity
import com.sdk.domain.entity.account.RegisterFieldsEntity

/**
 * Created by victor on 3/19/19
 */
class MapRegisterFields : ModelMapper<InPlayerRegisterFieldsModel, RegisterFieldsEntity> {
    
    
    override fun mapFromModel(model: InPlayerRegisterFieldsModel): RegisterFieldsEntity {
        return RegisterFieldsEntity(default_value = model.default_value,
                type = RegisterFieldTypeEntity.valueOf(model.type.toString()),
                label = model.label,
                required = model.required,
                id = model.id,
                options = model.options,
                name = model.name,
                placeholder = model.placeholder
        )
    }
    
    override fun mapToModel(entity: RegisterFieldsEntity): InPlayerRegisterFieldsModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}