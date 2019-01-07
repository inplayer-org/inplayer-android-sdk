package com.s.domain.entity.asset

/**
 * Created by victor on 1/5/19
 */
data class ItemAccessModel(val id: Long, val accountId: Long, val customerId: Long, val customerUUID: String, val ipAddress: String,
                           val countryCode : String, val createdAt : Long, val expiresAt : Long, val itemDetailsModel: ItemDetailsModel)