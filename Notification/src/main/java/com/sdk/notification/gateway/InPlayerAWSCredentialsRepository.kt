package com.sdk.notification.gateway

import com.sdk.notification.entity.InPlayerAWSCredentials
import io.reactivex.Single

interface InPlayerAWSCredentialsRepository {
    fun getAwsCredentials(): Single<InPlayerAWSCredentials>
}