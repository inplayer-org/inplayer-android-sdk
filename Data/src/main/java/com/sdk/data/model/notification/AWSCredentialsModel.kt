package com.sdk.data.model.notification

data class AWSCredentialsModel(
        val accessKey: String,
        val iotEndpoint: String,
        val region: String,
        val secretKey: String,
        val sessionToken: String
)