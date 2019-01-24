package com.sdk.notification.entity

data class InPlayerAWSCredentials(
        val accessKey: String,
        val iotEndpoint: String,
        val region: String,
        val secretKey: String,
        val sessionToken: String
) {
    
    var userUUID: String = ""
}