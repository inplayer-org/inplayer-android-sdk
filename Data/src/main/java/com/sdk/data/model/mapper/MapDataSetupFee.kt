package com.sdk.data.model.mapper

import com.sdk.data.model.asset.SetupFeeModel
import com.sdk.domain.entity.asset.SetupFeeEntity

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