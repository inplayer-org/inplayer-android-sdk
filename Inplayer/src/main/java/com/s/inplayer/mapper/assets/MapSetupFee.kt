package com.s.inplayer.mapper.assets

import com.s.domain.entity.asset.SetupFeeEntity
import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.assets.InPlayerSetupFee

/**
 * Created by victor on 1/6/19
 */
class MapSetupFee : DomainMapper<SetupFeeEntity, InPlayerSetupFee> {
    
    override fun mapFromDomain(domainEntity: SetupFeeEntity): InPlayerSetupFee {
        return InPlayerSetupFee(feeAmount = domainEntity.feeAmount,
                description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: InPlayerSetupFee): SetupFeeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}