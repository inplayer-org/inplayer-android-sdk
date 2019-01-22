package com.sdk.data.repository

import com.sdk.data.model.mapper.MapAWSCredentials
import com.sdk.data.repository.gateway.NotificationsRemote
import com.sdk.data.repository.gateway.UserLocalAuthenticator
import com.sdk.notification.entity.MyAWSCredentials
import com.sdk.notification.gateway.InPlayerAWSCredentialsRepository
import io.reactivex.Single

/**
 * Created by victor on 1/15/19
 */
class InPlayerAWSCredentialsRepositoryImpl constructor(private val notificationsRemote: NotificationsRemote,
                                                       private val userLocalAuthenticator: UserLocalAuthenticator,
                                                       private val mapAWSCredentials: MapAWSCredentials) : InPlayerAWSCredentialsRepository {
    
    override fun getAwsCredentials(): Single<MyAWSCredentials> {
        return notificationsRemote.getAwsCredentials().map {
            val model = mapAWSCredentials.mapFromModel(it)
            userLocalAuthenticator.getAccount()?.let { account ->
                model.userUUID = account.uuid
            }
            return@map model
        }
    }
    
}