package com.s.data.remote.request

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 12/27/18
 */
data class UpdateAccountRequest(@SerializedName("full_name") val fullName: String, @SerializedName("metadata") val metadata: HashMap<String, String>? = null)