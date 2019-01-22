package com.s.data.model.mapper

import com.s.data.model.asset.TrialPeriodModel
import com.s.domain.entity.asset.TrialPeriodEntity

/**
 * Created by victor on 1/6/19
 */
class MapDataTrialPeriod : ModelMapper<TrialPeriodModel, TrialPeriodEntity> {
    
    override fun mapFromModel(model: TrialPeriodModel): TrialPeriodEntity {
        return TrialPeriodEntity(quantity = model.quantity, period = model.period, description = model.description)
    }
    
    override fun mapToModel(entity: TrialPeriodEntity): TrialPeriodModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}