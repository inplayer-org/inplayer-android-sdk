package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.TrialPeriodEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.TrialPeriod

/**
 * Created by victor on 1/6/19
 */
class MapTrialPeriod : DomainMapper<TrialPeriodEntity, TrialPeriod> {
    
    override fun mapFromDomain(domainEntity: TrialPeriodEntity): TrialPeriod {
        return TrialPeriod(quantity = domainEntity.quantity, period = domainEntity.period, description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: TrialPeriod): TrialPeriodEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}