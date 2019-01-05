package com.s.domain.gateway

import com.s.domain.entity.asset.AccessFeeEntity
import com.s.domain.entity.asset.ItemAccessEntity
import com.s.domain.entity.asset.ItemDetailsEntity
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
interface InPlayerAssetsRepository {
    
    fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsEntity>
    
    fun getItemAccess(id: Int): Single<ItemAccessEntity>
    
    fun getAccessFees(id: Int): Single<AccessFeeEntity>
}