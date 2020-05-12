package com.sdk.domain.gateway

import com.sdk.domain.entity.asset.AccessFeeEntity
import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.entity.asset.ItemDetailsEntity
import io.reactivex.Single

interface InPlayerAssetsRepository {
    
    fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsEntity>
    
    fun getItemAccess(id: Int, entryId: String?): Single<ItemAccessEntity>
    
    fun getAccessFees(id: Int): Single<List<AccessFeeEntity>>
    
    fun getAccessFeesv2(id: Int, voucher: Int): Single<List<AccessFeeEntity>>
    
    fun getExternalAsset(
        assetType: String,
        externalId: String,
        merchantUUID: String
    ): Single<ItemDetailsEntity>
}