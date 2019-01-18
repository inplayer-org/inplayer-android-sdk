package com.s.data.model.mapper

import com.s.data.model.notification.AWSCredentialsModel
import com.s.notification.entity.MyAWSCredentials

/**
 * Created by victor on 1/15/19
 */
class MapAWSCredentials : ModelMapper<AWSCredentialsModel, MyAWSCredentials> {
    
    override fun mapFromModel(model: AWSCredentialsModel): MyAWSCredentials {
        return MyAWSCredentials(accessKey = model.accessKey, iotEndpoint = model.iotEndpoint, region = model.region, secretKey = model.secretKey, sessionToken = model.sessionToken)
    }
    
    override fun mapToModel(entity: MyAWSCredentials): AWSCredentialsModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}