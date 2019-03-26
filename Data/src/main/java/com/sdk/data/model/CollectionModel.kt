package com.sdk.data.model

/**
 * Created by victor on 3/16/19
 */
data class CollectionModel<T>(val total: Int, val page: Int, val offset: Int, val limit: Int, val collection: List<T>)
