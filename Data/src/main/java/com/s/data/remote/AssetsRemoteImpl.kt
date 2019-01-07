package com.s.data.remote

import com.s.data.remote.api.InPlayerRemoteProvider
import com.s.data.repository.gateway.AssetsRemote
import com.s.domain.entity.asset.AccessFeeModel
import com.s.domain.entity.asset.ItemAccessModel
import com.s.domain.entity.asset.ItemDetailsModel
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
class AssetsRemoteImpl constructor(val inPlayerRemoteProvider: InPlayerRemoteProvider) : AssetsRemote {
    
    override fun getItemAccess(id: Int, token: String): Single<ItemAccessModel> {
        return inPlayerRemoteProvider.getItemAccess(id, token)
    }
    
    override fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsModel> {
        return inPlayerRemoteProvider.getItemDetails(id = id, merchantUUID = merchantUUID)
    }
    
    override fun getAccessFees(id: Int): Single<List<AccessFeeModel>> {
        return inPlayerRemoteProvider.getAccessFees(id = id)
    }
    
}