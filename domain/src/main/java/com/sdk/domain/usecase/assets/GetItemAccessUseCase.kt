package com.sdk.domain.usecase.assets

import com.sdk.domain.entity.asset.ItemAccessEntity
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.schedulers.MySchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
class GetItemAccessUseCase(val mySchedulers: MySchedulers, val inPlayerAssetsRepository: InPlayerAssetsRepository)
    : SingleUseCase<ItemAccessEntity, GetItemAccessUseCase.Params>(mySchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<ItemAccessEntity> {
        
        params?.let {
            return inPlayerAssetsRepository.getItemAccess(it.id)
        }
        
        throw IllegalStateException("Params can't be null for GetItemAccessUseCase")
        
    }
    
    data class Params(val id: Int)
}