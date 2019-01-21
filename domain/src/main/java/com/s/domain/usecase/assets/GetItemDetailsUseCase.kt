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
            return inPlayerAssetsRepository.getItemDetails(it.id, it.merchantUUID)
        }
        
        throw IllegalStateException("Params can't be null for GetItemDetailsUseCase")
        
    }
    
    data class Params(val id: Int, val merchantUUID: String)
}