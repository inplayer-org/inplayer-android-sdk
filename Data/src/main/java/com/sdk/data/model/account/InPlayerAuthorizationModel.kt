package com.sdk.data.model.account

import com.google.gson.annotations.SerializedName

data class InPlayerAuthorizationModel(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("account") val account: InPlayerAccount,
    @SerializedName("expires") val expires: Long
)