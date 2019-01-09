package com.s.data.remote

import com.s.data.remote.api.InPlayerRemotePublicServiceAPI
import com.s.data.remote.api.InPlayerRemoteServiceAPI
import com.s.data.repository.gateway.AssetsRemote
import com.s.domain.entity.asset.AccessFeeModel
import com.s.domain.entity.asset.ItemAccessModel
import com.s.domain.entity.asset.ItemDetailsModel
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