package com.sdk.data.repository.gateway

import com.sdk.data.model.notification.AWSCredentialsModel
import io.reactivex.Single

/**
 * Created by victor on 1/15/19
 */
interface NotificationsRemote {
    
    fun getAwsCredentials(): Single<AWSCredentialsModel>
    
}