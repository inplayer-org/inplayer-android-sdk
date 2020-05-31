package com.sdk.domain.entity.asset


data class ItemDetailsEntity(
    val id: Long,
    val merchantId: Long,
    val merchantUUID: String,
    val isActive: Boolean,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val accessFees: List<AccessFeeEntity>,
    val accessControlType: AccessControlTypeEntity?,
    val itemType: ItemTypeEntity?,
    val metahash: Map<String, String>,
    val metadata: List<ItemMetadataEntity>,
    val content: String?
)