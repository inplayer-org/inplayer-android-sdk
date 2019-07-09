package com.s.domain.usecase.assets

import com.s.domain.entity.asset.ItemDetailsEntity
import com.s.domain.gateway.InPlayerAssetsRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
class GetItemDetailsUseCase(mySchedulers: MySchedulers, val inPlayerAssetsRepository: InPlayerAssetsRepository)
    : SingleUseCase<ItemDetailsEntity, GetItemDetailsUseCase.Params>(mySchedulers) {

    override fun buildUseCaseObservable(params: Params?): Single<ItemDetailsEntity> {

        params?.let {
            return when(params){
                is Params.ItemDetailsParams -> inPlayerAssetsRepository.getItemDetails(params.id, params.merchantUUID)
                is Params.ExternalAssetParams -> inPlayerAssetsRepository.getExternalAsset(params.assetType, params.externalId)
            }
        }

        throw IllegalStateException("Params can't be null for GetItemDetailsUseCase")

    }

    sealed class Params {
        data class ItemDetailsParams(val id: Int, val merchantUUID: String) : Params()
        data class ExternalAssetParams(val assetType: String, val externalId: String) : Params()
    }
}