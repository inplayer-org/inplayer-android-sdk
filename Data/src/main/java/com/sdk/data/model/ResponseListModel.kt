package com.sdk.data.model

/**
 * Created by victor on 3/13/19
 */
data class ResponseListModel<T>(val total: Int, val collection: List<T>)