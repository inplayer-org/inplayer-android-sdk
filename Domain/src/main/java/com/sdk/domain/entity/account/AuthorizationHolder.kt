package com.sdk.domain.entity.account


data class AuthorizationHolder (val accessToken: String, val refreshToken: String, val account :InPlayerDomainUser)