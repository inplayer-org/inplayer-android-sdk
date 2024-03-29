package com.sdk.data.model.mapper

import com.sdk.data.model.asset.ItemAccessModel
import com.sdk.domain.entity.asset.ItemAccessEntity


class MapDataItemAccess constructor() :
    ModelMapper<ItemAccessModel, ItemAccessEntity> {
    
    override fun mapFromModel(model: ItemAccessModel): ItemAccessEntity {
        return ItemAccessEntity(
            id = model.id,
            accountId = model.accountId,
            customerId = model.customerId,
            customerUUID = model.customerUUID ?: "",
            ipAddress = model.ipAddress ?: "",
            countryCode = model.countryCode ?: "",
            createdAt = model.createdAt,
            expiresAt = model.expiresAt,
            itemDetailsEntity = model.itemDetailsModel?.let { it.mapToEntity() },
            merchantUUID = model.merchantUUID ?: "",
            accessControlTypeEntity = null,
            merchantId = model.merchantId,
            active = model.isActive ?: false || model.active ?: false,
            content = "",
            title = ""
        )
    }
    
    override fun mapToModel(entity: ItemAccessEntity): ItemAccessModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}