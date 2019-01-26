package com.sdk.inplayer.model.assets


 class InPlayerItem(val id: Long,
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