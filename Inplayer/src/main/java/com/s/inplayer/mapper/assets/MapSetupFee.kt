package com.s.inplayer.mapper.assets

import com.s.inplayer.model.assets.SetupFee
import com.s.domain.entity.asset.SetupFeeEntity
import com.s.domain.entity.mapper.DomainMapper

/**
 * Created by victor on 1/6/19
 */
class MapSetupFee : DomainMapper<SetupFeeEntity, SetupFee> {
    
    override fun mapFromDomain(domainEntity: SetupFeeEntity): SetupFee {
        return SetupFee(feeAmount = domainEntity.feeAmount, description = domainEntity.description)
    }
    
    override fun mapToDomain(viewModel: SetupFee): SetupFeeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}