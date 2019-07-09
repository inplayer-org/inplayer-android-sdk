package com.s.data.repository.gateway

import com.s.domain.entity.asset.AccessFeeModel
import com.s.domain.entity.asset.ItemAccessModel
import com.s.domain.entity.asset.ItemDetailsModel
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
interface AssetsRemote {

    fun getItemAccess(id: Int): Single<ItemAccessModel>

    fun getItemDetails(id: Int, merchantUUID: String): Single<ItemDetailsModel>

    fun getExternalAsset(assetType: String, externalId: String): Single<ItemDetailsModel>

    fun getAccessFees(id: Int): Single<List<AccessFeeModel>>
}