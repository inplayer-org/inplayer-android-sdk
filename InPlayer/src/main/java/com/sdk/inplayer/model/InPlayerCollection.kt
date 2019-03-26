package com.sdk.inplayer.model

data class InPlayerCollection<T>(
        val collection: List<T>,
        val limit: Int,
        val offset: Int,
        val page: Int,
        val total: Int
)