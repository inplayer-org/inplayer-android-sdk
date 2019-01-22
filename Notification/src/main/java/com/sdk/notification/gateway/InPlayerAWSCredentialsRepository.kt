package com.sdk.notification.gateway

import com.sdk.notification.entity.MyAWSCredentials
import io.reactivex.Single

/**
 * Created by victor on 1/15/19
 */
interface InPlayerAWSCredentialsRepository {
    
    fun getAwsCredentials(): Single<MyAWSCredentials>
}