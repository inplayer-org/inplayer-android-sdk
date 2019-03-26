package com.sdk.data.remote

import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.SubscriptionRemote

/**
 * Created by victor on 3/16/19
 */
class SubscriptionRemoteImpl constructor(private val inPlayerRemoteProvider: InPlayerRemoteServiceAPI) : SubscriptionRemote {
    
    override fun getSubscriptions(page: Int, limit: Int) = inPlayerRemoteProvider.getSubscriptions(page, limit)
    
}