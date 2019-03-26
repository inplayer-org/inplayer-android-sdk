package com.sdk.inplayer.service

import com.sdk.domain.usecase.subscription.GetSubscriptionsUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Created by victor on 3/16/19
 */
class SubscriptionService : KoinComponent {
    
    val getSubscriptionsUseCase: GetSubscriptionsUseCase by inject()
}