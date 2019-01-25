package com.sdk.data.repository

import com.sdk.data.model.mapper.MapDataAccessFee
import com.sdk.data.model.mapper.MapDataItemAccess
import com.sdk.data.model.mapper.MapDataItemDetails
import com.sdk.data.repository.gateway.AssetsRemote
import com.sdk.domain.entity.asset.AccessFeeEntity
import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.entity.asset.ItemDetailsEntity
import com.sdk.domain.gateway.InPlayerAssetsRepository
import io.reactivex.Single


class InPlayerAssetsRepositoryImpl(private val assetsRemote: AssetsRemote,
                                   private val mapAccessFee: MapDataAccessFee,
                                   private val mapItemDetails: MapDataItemDetails,
                                   private val mapItemAccess: MapDataItemAccess) : InPlayerAssetsRepository {
    
    override fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsEntity> {
        return assetsRemote.getItemDetails(id = id, merchantUUID = merchantUUID).map {
            mapItemDetails.mapFromModel(it)
        }
    }
    
    override fun getItemAccess(id: Int): Single<ItemAccessEntity> {
        return assetsRemote.getItemAccess(id = id)
                .map { mapItemAccess.mapFromModel(it) }
    }
    
    override fun getAccessFees(id: Int): Single<List<AccessFeeEntity>> {
        return assetsRemote.getAccessFees(id).map { list ->
            list.map { mapAccessFee.mapFromModel(it) }
        }
    }
    
}