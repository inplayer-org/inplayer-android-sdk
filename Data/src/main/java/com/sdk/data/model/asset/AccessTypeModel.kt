package com.sdk.data.model.asset

import com.sdk.domain.entity.asset.AccessTypeEntity


data class AccessTypeModel(
    val id: Long,
    val account_id: Long,
    val name: String?,
    val quantity: Int,
    val period: String?
) {
    
    fun mapToEntity(): AccessTypeEntity {
        return AccessTypeEntity(
            id = id,
            quantity = quantity,
            period = period ?: "",
            name = name ?: ""
        )
    }
}