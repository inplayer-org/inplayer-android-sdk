package com.s.inplayer.model.account

/**
 * Created by victor on 1/21/19
 */
data class InPlayerAuthorizationModel(val accessToken : String, val refreshToken : String, val account : InPlayerUser)