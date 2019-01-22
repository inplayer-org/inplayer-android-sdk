package com.s.data.repository.gateway

import com.s.data.model.asset.AccessFeeModel
import com.s.data.model.asset.ItemAccessModel
import com.s.data.model.asset.ItemDetailsModel
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
interface AssetsRemote {
    
    fun getItemAccess(id: Int): Single<ItemAccessModel>
    
    fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsModel>
    
    fun getAccessFees(id: Int): Single<List<AccessFeeModel>>
}