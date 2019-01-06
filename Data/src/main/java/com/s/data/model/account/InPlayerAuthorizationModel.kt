package com.s.data.model.account

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 12/25/18
 */
data class InPlayerAuthorizationModel(@SerializedName("access_token") val accessToken: String, @SerializedName("refresh_token") val refreshToken: String?, val account: InPlayerAccount)