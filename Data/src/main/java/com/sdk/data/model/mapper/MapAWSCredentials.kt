package com.sdk.data.model.mapper

import com.sdk.data.model.notification.AWSCredentialsModel
import com.sdk.notification.entity.InPlayerAWSCredentials


class MapAWSCredentials : ModelMapper<AWSCredentialsModel, InPlayerAWSCredentials> {
    
    override fun mapFromModel(model: AWSCredentialsModel): InPlayerAWSCredentials {
        return InPlayerAWSCredentials(accessKey = model.accessKey, iotEndpoint = model.iotEndpoint, region = model.region, secretKey = model.secretKey, sessionToken = model.sessionToken)
    }
    
    override fun mapToModel(entity: InPlayerAWSCredentials): AWSCredentialsModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}