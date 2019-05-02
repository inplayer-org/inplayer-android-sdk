package com.sdk.inplayer.api

import com.sdk.domain.entity.subscribtion.SubscriptionEntity
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.subscription.GetSubscriptionsUseCase
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.MapInPlayerCollection
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.model.InPlayerCollection
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.model.subscription.InPlayerSubscription
import com.sdk.inplayer.service.SubscriptionService

/**
 * Created by victor on 3/16/19
 */
class Subscription internal constructor(private val appSchedulers: InPlayerSchedulers,
                                        private val mapInPlayerCollection: MapInPlayerCollection<SubscriptionEntity, InPlayerSubscription>,
                                        private val subscriptionService: SubscriptionService) {
    
    /**
     * Get all the Customer/Merchant subscription records
     *
     *
     * @param page Integer The current page
     * @param limit Integer The number of items per page
     * @param callback InPlayerCallback<String, InPlayerException>
     */
    fun getSubscriptions(page: Int, limit: Int, callback: InPlayerCallback<InPlayerCollection<InPlayerSubscription>, InPlayerException>) {
        val disposable = subscriptionService.getSubscriptionsUseCase.execute(GetSubscriptionsUseCase.Params(page, limit))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(mapInPlayerCollection.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                    
                })
    }
}