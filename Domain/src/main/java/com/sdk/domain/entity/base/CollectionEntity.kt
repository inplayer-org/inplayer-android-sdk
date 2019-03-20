package com.sdk.domain.entity.base

/**
 * Created by victor on 3/16/19
 */
data class CollectionEntity<T>(val total: Int, val page: Int, val offset: Int, val limit: Int, val collection: List<T>)
