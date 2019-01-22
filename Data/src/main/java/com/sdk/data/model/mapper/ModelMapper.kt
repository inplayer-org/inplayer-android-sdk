package com.sdk.data.model.mapper

/**
 * Created by victor on 12/25/18
 */
interface ModelMapper<M, E> {
    
    fun mapFromModel(model: M): E
    
    fun mapToModel(entity: E): M
    
}