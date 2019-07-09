package com.s.data.repository

import com.s.data.model.mapper.MapDataAccessFee
import com.s.data.model.mapper.MapDataItemAccess
import com.s.data.model.mapper.MapDataItemDetails
import com.s.data.repository.gateway.AssetsRemote
import com.s.domain.entity.asset.AccessFeeEntity
import com.s.domain.entity.asset.ItemAccessEntity
import com.s.domain.entity.asset.ItemDetailsEntity
import com.s.domain.gateway.InPlayerAssetsRepository
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
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

    override fun getExternalAsset(assetType: String, externalId: String): Single<ItemDetailsEntity> {
        return assetsRemote.getExternalAsset(assetType = assetType, externalId = externalId)
                .map { mapItemDetails.mapFromModel(it) }
    }

    override fun getAccessFees(id: Int): Single<List<AccessFeeEntity>> {
        return assetsRemote.getAccessFees(id).map { list ->
            list.map { mapAccessFee.mapFromModel(it) }
        }
    }

}