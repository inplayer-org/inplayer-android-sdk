package com.sdk.data.remote

import com.sdk.data.BuildConfig
import com.sdk.data.model.notification.AWSCredentialsModel
import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.NotificationsRemote
import io.reactivex.Single


class NotificationsRemoteImpl constructor(private val isDebug: Boolean, private val inPlayerRemoteProvider: InPlayerRemoteServiceAPI) : NotificationsRemote {
    
    override fun getAwsCredentials(): Single<AWSCredentialsModel> {
        
        val awsUrl = if (isDebug) BuildConfig.AWS_CREDENTIALS_URL_STAGING else BuildConfig.AWS_CREDENTIALS_URL_PROD
        
        return inPlayerRemoteProvider.getAwsCredentials(awsUrl)
    }
}