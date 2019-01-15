package com.s.data.repository

import com.s.data.model.mapper.MapAWSCredentials
import com.s.data.repository.gateway.NotificationsRemote
import com.s.notification.entity.MyAWSCredentials
import com.s.notification.gateway.InPlayerAWSCredentialsRepository
import io.reactivex.Single

/**
 * Created by victor on 1/15/19
 */
class InPlayerAWSCredentialsRepositoryImpl constructor(private val notificationsRemote: NotificationsRemote, private val mapAWSCredentials: MapAWSCredentials) : InPlayerAWSCredentialsRepository {
    
    override fun getAwsCredentials(): Single<MyAWSCredentials> {
        return notificationsRemote.getAwsCredentials().map { mapAWSCredentials.mapFromModel(it) }
    }
    
}