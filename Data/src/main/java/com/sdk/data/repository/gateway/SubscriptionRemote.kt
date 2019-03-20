package com.sdk.data.repository.gateway

import com.sdk.data.model.CollectionModel
import com.sdk.data.model.subscription.SubscriptionModel
import io.reactivex.Single

/**
 * Created by victor on 3/16/19
 */
interface SubscriptionRemote {
    
    fun getSubscriptions(page: Int, limit: Int): Single<CollectionModel<SubscriptionModel>>
    
}