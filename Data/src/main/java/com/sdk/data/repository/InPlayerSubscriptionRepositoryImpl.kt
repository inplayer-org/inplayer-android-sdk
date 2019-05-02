package com.sdk.data.repository

import com.sdk.data.model.mapper.MapCollectionModel
import com.sdk.data.model.subscription.SubscriptionModel
import com.sdk.data.repository.gateway.SubscriptionRemote
import com.sdk.domain.entity.base.CollectionEntity
import com.sdk.domain.entity.subscribtion.SubscriptionEntity
import com.sdk.domain.gateway.InPlayerSubscriptionsRepository
import io.reactivex.Single

/**
 * Created by victor on 3/16/19
 */
class InPlayerSubscriptionRepositoryImpl constructor(private val subscriptionRemote: SubscriptionRemote,
                                                     private val mapCollectionModel: MapCollectionModel<SubscriptionModel, SubscriptionEntity>
) : InPlayerSubscriptionsRepository {
    
    override fun getSubscriptions(page: Int, limit: Int): Single<CollectionEntity<SubscriptionEntity>> {
        return subscriptionRemote.getSubscriptions(page, limit).map {
            return@map mapCollectionModel.mapFromModel(it)
        }
    }
    
}