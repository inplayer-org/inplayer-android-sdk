package com.sdk.data.model.account

import com.google.gson.annotations.SerializedName

/**
 * Created by Victor on 2019-04-24
 */
data class InPlayerSocialUrlsReponse(@SerializedName("social_urls") val socialUrls: ArrayList<HashMap<String, String>>)