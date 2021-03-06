package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.AccessFeeEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerAccessFee


internal class MapAccessFee constructor(private val mapAccessType: MapAccessType, private val mapItemType: MapItemType,
                               private val mapTrialPeriod: MapTrialPeriod, private val mapSetupFee: MapSetupFee)
    : DomainMapper<AccessFeeEntity, InPlayerAccessFee> {
    
    override fun mapFromDomain(domainEntity: AccessFeeEntity): InPlayerAccessFee {
        return InPlayerAccessFee(id = domainEntity.id,
                merchantId = domainEntity.merchantId,
                amount = domainEntity.amount,
                currency = domainEntity.currency,
                description = domainEntity.description,
                accessType = mapAccessType.mapFromDomain(domainEntity.accessTypeEntity),
                itemType = domainEntity.itemType,
                trialPeriod = domainEntity.trialPeriodEntity?.let { mapTrialPeriod.mapFromDomain(it) },
                setupFee = domainEntity.setupFeeEntity?.let { mapSetupFee.mapFromDomain(it) },
                expiresAt = domainEntity.expiresAt)
    }
    
    override fun mapToDomain(viewModel: InPlayerAccessFee): AccessFeeEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}