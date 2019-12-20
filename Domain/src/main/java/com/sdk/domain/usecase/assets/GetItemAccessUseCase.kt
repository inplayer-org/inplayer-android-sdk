package com.sdk.domain.usecase.assets

import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class GetItemAccessUseCase(private val inPlayerSchedulers: InPlayerSchedulers, private val inPlayerAssetsRepository: InPlayerAssetsRepository)
    : SingleUseCase<ItemAccessEntity, GetItemAccessUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<ItemAccessEntity> {
        
        params?.let {
            return inPlayerAssetsRepository.getItemAccess(it.id, it.entryId)
        }
        
        throw IllegalStateException("Params can't be null for GetItemAccessUseCase")
        
    }
    
    data class Params(val id: Int, val entryId: String? = null)
}