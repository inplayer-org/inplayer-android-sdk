package com.sdk.data.remote

import com.sdk.data.model.asset.AccessFeeModel
import com.sdk.data.model.asset.ItemAccessModel
import com.sdk.data.model.asset.ItemDetailsModel
import com.sdk.data.remote.api.InPlayerRemotePublicServiceAPI
import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.AssetsRemote
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
class AssetsRemoteImpl constructor(private val inPlayerRemoteServiceAPI: InPlayerRemoteServiceAPI,
                                   private val inPlayerRemotePublicServiceAPI: InPlayerRemotePublicServiceAPI) : AssetsRemote {
    
    override fun getItemAccess(id: Int): Single<ItemAccessModel> {
        return inPlayerRemoteServiceAPI.getItemAccess(id)
    }
    
    override fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsModel> {
        return inPlayerRemotePublicServiceAPI.getItemDetails(id = id, merchantUUID = merchantUUID)
    }
    
    override fun getAccessFees(id: Int): Single<List<AccessFeeModel>> {
        return inPlayerRemotePublicServiceAPI.getAccessFees(id = id)
    }
    
}