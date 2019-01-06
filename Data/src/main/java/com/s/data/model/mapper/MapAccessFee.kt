package com.s.data.model.mapper

import com.s.domain.entity.asset.AccessFeeEntity
import com.s.domain.entity.asset.AccessFeeModel

/**
 * Created by victor on 1/6/19
 */
class MapAccessFee constructor(private val mapAccessType: MapAccessType, private val mapItemType: MapItemType,
                               private val mapTrialPeriod: MapTrialPeriod, private val mapSetupFee: MapSetupFee) : ModelMapper<AccessFeeModel, AccessFeeEntity> {
    
    override fun mapFromModel(model: AccessFeeModel): AccessFeeEntity {
        
        return AccessFeeEntity(id = model.id, merchantId = model.merchantId, amount = model.amount, currency = model.currency, description = model.description,
                accessTypeEntity = mapAccessType.mapFromModel(model.accessTypeModel), itemTypeEntity = mapItemType.mapFromModel(model.itemTypeModel),
                trialPeriodEntity = mapTrialPeriod.mapFromModel(model.trialPeriodModel), setupFeeEntity = mapSetupFee.mapFromModel(model.setupFeeModel), expiresAt = model.expiresAt)
    }
    
    override fun mapToModel(entity: AccessFeeEntity): AccessFeeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}