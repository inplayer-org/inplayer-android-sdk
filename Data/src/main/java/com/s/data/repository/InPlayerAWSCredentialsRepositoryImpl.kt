package com.s.data.repository

import com.s.data.model.mapper.MapAWSCredentials
import com.s.data.repository.gateway.NotificationsRemote
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.notification.entity.MyAWSCredentials
import com.s.notification.gateway.InPlayerAWSCredentialsRepository
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