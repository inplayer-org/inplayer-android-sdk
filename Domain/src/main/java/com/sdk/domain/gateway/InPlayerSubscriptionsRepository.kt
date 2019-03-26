package com.sdk.domain.gateway

import com.sdk.domain.entity.base.CollectionEntity
import com.sdk.domain.entity.subscribtion.SubscriptionEntity
import io.reactivex.Single

/**
 * Created by victor on 3/16/19
 */
interface InPlayerSubscriptionsRepository {
    
    fun getSubscriptions(page: Int, limit: Int): Single<CollectionEntity<SubscriptionEntity>>
    
}