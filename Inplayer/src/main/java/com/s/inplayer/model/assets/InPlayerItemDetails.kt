package com.s.inplayer.model.assets

/**
 * Created by victor on 1/5/19
 */
data class InPlayerItemDetails(val id: Long, val merchantId: Long, val merchantUUID: String, val isActive: Boolean, val title: String,
                               val inPlayerAccessControlTypeEntity: InPlayerAccessControlType, val inPlayerItemTypeEntity: InPlayerItemType, val metaData: Map<String, String>, val createdAt: Long, val updatedAt: Long)