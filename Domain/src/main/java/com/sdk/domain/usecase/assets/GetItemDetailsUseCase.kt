package com.sdk.domain.usecase.assets

import com.sdk.domain.entity.asset.ItemDetailsEntity
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class GetItemDetailsUseCase(inPlayerSchedulers: InPlayerSchedulers, val inPlayerAssetsRepository: InPlayerAssetsRepository)
    : SingleUseCase<ItemDetailsEntity, GetItemDetailsUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<ItemDetailsEntity> {
        
        params?.let {
            return inPlayerAssetsRepository.getItemDetails(it.id, it.merchantUUID)
        }
        
        throw IllegalStateException("Params can't be null for GetItemDetailsUseCase")
        
    }
    
    data class Params(val id: Int, val merchantUUID: String)
}