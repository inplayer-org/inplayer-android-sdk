package com.sdk.data.repository.gateway

import com.sdk.data.model.asset.AccessFeeModel
import com.sdk.data.model.asset.ItemAccessModel
import com.sdk.data.model.asset.ItemDetailsModel
import com.sdk.data.model.asset.v2.AccessFeeModelV2
import io.reactivex.Single

interface AssetsRemote {
    
    fun getItemAccess(id: Int, entryId: String?): Single<ItemAccessModel>
    
    fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsModel>
    
    fun getAccessFees(id: Int): Single<List<AccessFeeModel>>
    
    fun getExternalAsset(assetType: String, externalId: String, merchantUUID: String): Single<ItemDetailsModel>
    
    fun getAccessFeesV2(id: Int, voucher: Int?): Single<List<AccessFeeModelV2>>
}
