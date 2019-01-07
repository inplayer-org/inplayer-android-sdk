package com.s.inplayer.model.assets

/**
 * Created by victor on 1/5/19
 */
data class ItemDetails(val id: Long, val merchantId: Long, val merchantUUID: String, val isActive: Boolean, val title: String,
                       val accessControlTypeEntity: AccessControlType, val itemTypeEntity: ItemType, val metaData: Map<String, String>, val createdAt: Long, val updatedAt: Long)