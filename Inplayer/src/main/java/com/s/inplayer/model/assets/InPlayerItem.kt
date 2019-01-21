package com.s.inplayer.model.assets

/**
 * Created by victor on 1/5/19
 */
data class InPlayerItem(val id: Long,
                        val merchantId: Long,
                        val merchantUUID: String,
                        val isActive: Boolean,
                        val title: String,
                        val createdAt: Long,
                        val updatedAt: Long,
                        val accessFees: List<InPlayerAccessFee>,
                        val accessControlType: InPlayerAccessControlType,
                        val itemType: InPlayerItemType,
                        val metahash: Map<String, String>,
                        val metadata: List<ItemMetadata>)