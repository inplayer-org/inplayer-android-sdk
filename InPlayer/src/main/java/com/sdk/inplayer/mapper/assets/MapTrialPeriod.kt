package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.TrialPeriodEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerTrialPeriod


internal class MapTrialPeriod : DomainMapper<TrialPeriodEntity, InPlayerTrialPeriod> {
    
    override fun mapFromDomain(domainEntity: TrialPeriodEntity): InPlayerTrialPeriod {
        return InPlayerTrialPeriod(quantity = domainEntity.quantity,
                period = domainEntity.period,
                description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: InPlayerTrialPeriod): TrialPeriodEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}