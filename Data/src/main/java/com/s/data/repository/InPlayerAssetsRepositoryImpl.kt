package com.s.data.repository

import com.s.data.model.mapper.MapAccessFee
import com.s.data.model.mapper.MapItemAccess
import com.s.data.model.mapper.MapItemDetails
import com.s.data.repository.gateway.AssetsRemote
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.domain.entity.asset.AccessFeeEntity
import com.s.domain.entity.asset.ItemAccessEntity
import com.s.domain.entity.asset.ItemDetailsEntity
import com.s.domain.gateway.InPlayerAssetsRepository
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
class InPlayerAssetsRepositoryImpl(val assetsRemote: AssetsRemote,
                                   val userLocalAuthenticator: UserLocalAuthenticator,
                                   val mapAccessFee: MapAccessFee,
                                   val mapItemDetails: MapItemDetails,
                                   val mapItemAccess: MapItemAccess) : InPlayerAssetsRepository {
    
    override fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsEntity> {
        return assetsRemote.getItemDetails(id = id, merchantUUID = merchantUUID).map {
            mapItemDetails.mapFromModel(it)
        }
    }
    
    override fun getItemAccess(id: Int): Single<ItemAccessEntity> {
        return assetsRemote.getItemAccess(id = id, token = userLocalAuthenticator.getBearerAuthToken()).map { mapItemAccess.mapFromModel(it) }
    }
    
    override fun getAccessFees(id: Int): Single<AccessFeeEntity> {
        return assetsRemote.getAccessFees(id).map { mapAccessFee.mapFromModel(it) }
    }
    
}