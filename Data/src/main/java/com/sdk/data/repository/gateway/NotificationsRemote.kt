package com.sdk.data.repository.gateway

import com.sdk.data.model.notification.AWSCredentialsModel
import io.reactivex.Single

interface NotificationsRemote {
    
    fun getAwsCredentials(): Single<AWSCredentialsModel>
    
}