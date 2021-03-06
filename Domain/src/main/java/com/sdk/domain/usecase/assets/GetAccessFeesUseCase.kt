package com.sdk.domain.usecase.assets

import com.sdk.domain.entity.asset.AccessFeeEntity
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class GetAccessFeesUseCase(inPlayerSchedulers: InPlayerSchedulers, private val inPlayerAssetsRepository: InPlayerAssetsRepository)
    : SingleUseCase<List<AccessFeeEntity>, GetAccessFeesUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<List<AccessFeeEntity>> {
        
        params?.let {
            return inPlayerAssetsRepository.getAccessFees(it.id)
        }
        
        throw IllegalStateException("Params can't be null for GetAccessFeesUseCase")
        
    }
    
    data class Params(val id: Int)
}