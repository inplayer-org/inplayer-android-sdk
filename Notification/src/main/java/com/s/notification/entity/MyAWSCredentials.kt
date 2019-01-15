package com.s.notification.entity

data class MyAWSCredentials(
        val accessKey: String,
        val iotEndpoint: String,
        val region: String,
        val secretKey: String,
        val sessionToken: String
)