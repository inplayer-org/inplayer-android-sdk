package com.s.data.remote

import com.s.data.BuildConfig
import com.s.data.model.notification.AWSCredentialsModel
import com.s.data.remote.api.InPlayerRemoteServiceAPI
import com.s.data.repository.gateway.NotificationsRemote
import io.reactivex.Single

/**
 * Created by victor on 1/15/19
 */
class NotificationsRemoteImpl constructor(private val isDebug: Boolean, private val inPlayerRemoteProvider: InPlayerRemoteServiceAPI) : NotificationsRemote {
    
    override fun getAwsCredentials(): Single<AWSCredentialsModel> {
        
        val awsUrl = if (isDebug) BuildConfig.AWS_CREDENTIALS_URL_STAGING else BuildConfig.AWS_CREDENTIALS_URL_PROD
        
        return inPlayerRemoteProvider.getAwsCredentials(awsUrl)
    }
}