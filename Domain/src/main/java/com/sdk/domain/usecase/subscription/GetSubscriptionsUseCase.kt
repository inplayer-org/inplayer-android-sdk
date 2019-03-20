package com.sdk.domain.usecase.subscription

import com.sdk.domain.entity.base.CollectionEntity
import com.sdk.domain.entity.subscribtion.SubscriptionEntity
import com.sdk.domain.gateway.InPlayerSubscriptionsRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 3/16/19
 */
class GetSubscriptionsUseCase constructor(private val inPlayerSchedulers: InPlayerSchedulers,
                                          private val inPlayerSubscriptionsRepository: InPlayerSubscriptionsRepository)
    : SingleUseCase<CollectionEntity<SubscriptionEntity>, GetSubscriptionsUseCase.Params>(inPlayerSchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<CollectionEntity<SubscriptionEntity>> {
        
        params?.let {
            return inPlayerSubscriptionsRepository.getSubscriptions(it.page, it.limit)
        }
        
        throw IllegalStateException("Params can't be null for GetSubscriptionsUseCase")
    }
    
    
    data class Params(val page: Int, val limit: Int)
}
