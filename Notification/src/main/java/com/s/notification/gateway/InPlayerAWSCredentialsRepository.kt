package com.s.notification.gateway

import com.s.notification.entity.MyAWSCredentials
import io.reactivex.Single

/**
 * Created by victor on 1/15/19
 */
interface InPlayerAWSCredentialsRepository {
    
    fun getAwsCredentials(): Single<MyAWSCredentials>
}