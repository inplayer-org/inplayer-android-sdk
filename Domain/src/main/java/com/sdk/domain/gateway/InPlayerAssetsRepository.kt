package com.sdk.domain.gateway

import com.sdk.domain.entity.asset.AccessFeeEntity
import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.entity.asset.ItemDetailsEntity
import io.reactivex.Single


interface InPlayerAssetsRepository {
    
    fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsEntity>
    
    fun getItemAccess(id: Int): Single<ItemAccessEntity>
    
    fun getAccessFees(id: Int): Single<List<AccessFeeEntity>>
}