package com.sdk.inplayer.mapper.assets

import com.sdk.domain.entity.asset.ItemDetailsEntity
import com.sdk.domain.entity.asset.ItemMetadataEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerItem
import com.sdk.inplayer.model.assets.ItemMetadata

/**
 * Created by victor on 1/6/19
 */
class MapItemDetails constructor(private val mapAccessControlType: MapAccessControlType,
                                 private val mapItemType: MapItemType,
                                 private val mapAccessFee: MapAccessFee)
    : DomainMapper<ItemDetailsEntity, InPlayerItem> {
    
    
    override fun mapFromDomain(domainEntity: ItemDetailsEntity): InPlayerItem {
        return InPlayerItem(id = domainEntity.id,
                merchantId = domainEntity.merchantId,
                merchantUUID = domainEntity.merchantUUID,
                isActive = domainEntity.isActive,
                title = domainEntity.title,
                accessControlType = mapAccessControlType.mapFromDomain(domainEntity.accessControlType),
                itemType = mapItemType.mapFromDomain(domainEntity.itemType),
                metahash = domainEntity.metahash,
                createdAt = domainEntity.createdAt,
                updatedAt = domainEntity.updatedAt,
                metadata = domainEntity.metadata.map { mapItemMetadata(it) },
                accessFees = domainEntity.accessFees.map { mapAccessFee.mapFromDomain(it) })
    }
    
    
    private fun mapItemMetadata(itemMetadata: ItemMetadataEntity): ItemMetadata {
        return ItemMetadata(
                id = itemMetadata.id,
                name = itemMetadata.name,
                value = itemMetadata.value)
    }
    
    override fun mapToDomain(viewModel: InPlayerItem): ItemDetailsEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}
