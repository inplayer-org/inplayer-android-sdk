package com.s.data.model.mapper

import com.s.domain.entity.asset.TrialPeriodEntity
import com.s.domain.entity.asset.TrialPeriodModel

/**
 * Created by victor on 1/6/19
 */
class MapTrialPeriod : ModelMapper<TrialPeriodModel, TrialPeriodEntity> {
    
    override fun mapFromModel(model: TrialPeriodModel): TrialPeriodEntity {
        return TrialPeriodEntity(quantity = model.quantity, period = model.period, description = model.description)
    }
    
    override fun mapToModel(entity: TrialPeriodEntity): TrialPeriodModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}