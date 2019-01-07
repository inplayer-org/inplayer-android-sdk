package com.s.data.model.mapper

import com.s.domain.entity.asset.SetupFeeEntity
import com.s.domain.entity.asset.SetupFeeModel

/**
 * Created by victor on 1/6/19
 */
class MapDataSetupFee : ModelMapper<SetupFeeModel, SetupFeeEntity> {
    
    override fun mapFromModel(model: SetupFeeModel): SetupFeeEntity {
        return SetupFeeEntity(feeAmount = model.feeAmount, description = model.description)
    }
    
    override fun mapToModel(entity: SetupFeeEntity): SetupFeeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}