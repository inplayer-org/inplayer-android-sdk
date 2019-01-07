package com.s.data.model.mapper

import com.s.domain.entity.asset.AccessFeeEntity
import com.s.domain.entity.asset.AccessFeeModel

/**
 * Created by victor on 1/6/19
 */
class MapDataAccessFee constructor(private val mapAccessType: MapDataAccessType, private val mapItemType: MapDataItemType,
                                   private val mapTrialPeriod: MapDataTrialPeriod, private val mapSetupFee: MapDataSetupFee) : ModelMapper<AccessFeeModel, AccessFeeEntity> {
    
    override fun mapFromModel(model: AccessFeeModel): AccessFeeEntity {
        
        return AccessFeeEntity(id = model.id,
                merchantId = model.merchantId,
                amount = model.amount,
                currency = model.currency,
                description = model.description,
                accessTypeEntity = mapAccessType.mapFromModel(model.accessTypeModel),
                itemType = model.itemType,
                trialPeriodEntity = model.trialPeriodModel?.let { mapTrialPeriod.mapFromModel(it) },
                setupFeeEntity = model.setupFeeModel?.let { mapSetupFee.mapFromModel(it) },
                expiresAt = model.expiresAt)
    }
    
    override fun mapToModel(entity: AccessFeeEntity): AccessFeeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}