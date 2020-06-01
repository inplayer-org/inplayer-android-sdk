package com.sdk.data.remote

import com.sdk.data.model.asset.AccessFeeModel
import com.sdk.data.model.asset.ItemAccessModel
import com.sdk.data.model.asset.ItemDetailsModel
import com.sdk.data.remote.api.InPlayerRemotePublicServiceAPI
import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.AssetsRemote
import io.reactivex.Single

class AssetsRemoteImpl constructor(
    private val inPlayerRemoteServiceAPI: InPlayerRemoteServiceAPI,
    private val inPlayerRemotePublicServiceAPI: InPlayerRemotePublicServiceAPI
) : AssetsRemote {
    
    override fun getItemAccess(id: Int, entryId: String?): Single<ItemAccessModel> {
        return inPlayerRemoteServiceAPI.getItemAccess(id, entryId)
    }
    
    override fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsModel> {
        return inPlayerRemotePublicServiceAPI.getItemDetails(id = id, merchantUUID = merchantUUID)
    }
    
    override fun getExternalAsset(
        assetType: String,
        externalId: String,
        merchantUUID: String
    ): Single<ItemDetailsModel> {
        return inPlayerRemotePublicServiceAPI.getExternalAsset(
            assetType = assetType,
            externalId = externalId,
            merchantUUID = merchantUUID
        )
    }
    
    override fun getAccessFees(id: Int): Single<List<AccessFeeModel>> {
        return inPlayerRemotePublicServiceAPI.getAccessFees(id = id)
    }
    
    override fun getAccessFeesV2(id: Int, voucher: Int?): Single<List<AccessFeeModel>> {
        return inPlayerRemotePublicServiceAPI.getAccessFeesV2(id = id, voucher = voucher)
    }
}