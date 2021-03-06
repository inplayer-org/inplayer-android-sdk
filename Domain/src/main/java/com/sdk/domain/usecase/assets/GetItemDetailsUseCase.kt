package com.sdk.domain.usecase.assets

import com.sdk.domain.entity.asset.ItemDetailsEntity
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class GetItemDetailsUseCase(
    inPlayerSchedulers: InPlayerSchedulers,
    private val inPlayerAssetsRepository: InPlayerAssetsRepository
) : SingleUseCase<ItemDetailsEntity, GetItemDetailsUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<ItemDetailsEntity> {
        
        params?.let {
            return when (params) {
                is Params.ItemDetailsParams -> inPlayerAssetsRepository.getItemDetails(
                    params.id,
                    params.merchantUUID
                )
                is Params.ExternalAssetParams -> inPlayerAssetsRepository.getExternalAsset(
                    params.assetType,
                    params.externalId,
                    params.merchantUUID
                )
            }
        }
        
        throw IllegalStateException("Params can't be null for GetItemDetailsUseCase")
        
    }
    
    sealed class Params {
        data class ItemDetailsParams(val id: Int, val merchantUUID: String) : Params()
        data class ExternalAssetParams(
            val assetType: String,
            val externalId: String,
            val merchantUUID: String
        ) : Params()
    }
}