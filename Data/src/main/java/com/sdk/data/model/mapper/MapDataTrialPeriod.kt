package com.sdk.data.model.mapper

import com.sdk.data.model.asset.TrialPeriodModel
import com.sdk.domain.entity.asset.TrialPeriodEntity


class MapDataTrialPeriod : ModelMapper<TrialPeriodModel, TrialPeriodEntity> {
    
    override fun mapFromModel(model: TrialPeriodModel): TrialPeriodEntity {
        return TrialPeriodEntity(
            quantity = model.quantity,
            period = model.period ?: "",
            description = model.description ?: ""
        )
    }
    
    override fun mapToModel(entity: TrialPeriodEntity): TrialPeriodModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}