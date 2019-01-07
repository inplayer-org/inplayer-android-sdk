package com.s.domain.entity.asset

/**
 * Created by victor on 1/5/19
 */
data class ItemDetailsEntity(val id: Long, val merchantId: Long, val merchantUUID: String, val isActive: Boolean, val title: String,
                             val accessControlTypeEntity: AccessControlTypeEntity, val itemTypeEntity: ItemTypeEntity, val metaData: Map<String, String>, val createdAt: Long, val updatedAt: Long)