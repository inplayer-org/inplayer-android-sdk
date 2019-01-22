package com.s.domain.entity.account

/**
 * Created by victor on 1/21/19
 */
data class AuthorizationHolder (val accessToken: String, val refreshToken: String, val account :InPlayerDomainUser)