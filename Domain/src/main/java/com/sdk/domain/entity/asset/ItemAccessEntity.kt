package com.sdk.domain.entity.asset


data class ItemAccessEntity(val id: Long, val accountId: Long, val customerId: Long, val customerUUID: String, val ipAddress: String,
                            val countryCode: String, val createdAt: Long, val expiresAt: Long, val itemDetailsEntity: ItemDetailsEntity)