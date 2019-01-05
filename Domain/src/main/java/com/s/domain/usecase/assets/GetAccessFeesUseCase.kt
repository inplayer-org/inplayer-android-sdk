package com.s.domain.usecase.assets

import com.s.domain.entity.asset.AccessFeeEntity
import com.s.domain.gateway.InPlayerAssetsRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 1/5/19
 */
class GetAccessFeesUseCase(val mySchedulers: MySchedulers, val inPlayerAssetsRepository: InPlayerAssetsRepository)
    : SingleUseCase<AccessFeeEntity, GetAccessFeesUseCase.Params>(mySchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<AccessFeeEntity> {
        
        params?.let {
            return inPlayerAssetsRepository.getAccessFees(it.id)
        }
        
        throw IllegalStateException("Params can't be null for GetAccessFeesUseCase")
        
    }
    
    data class Params(val id: Int)
}