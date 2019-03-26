package com.sdk.data.model.mapper

import com.sdk.data.model.CollectionModel
import com.sdk.domain.entity.base.CollectionEntity

/**
 * Created by victor on 3/16/19
 */
class MapCollectionModel<M, E> constructor(private val collectionMapper: ModelMapper<M, E>) : ModelMapper<CollectionModel<M>, CollectionEntity<E>> {
    
    
    override fun mapFromModel(model: CollectionModel<M>): CollectionEntity<E> {
        return CollectionEntity(
                total = model.total,
                page = model.page,
                collection = model.collection.map { collectionMapper.mapFromModel(it) },
                offset = model.offset,
                limit = model.limit
        )
    }
    
    override fun mapToModel(entity: CollectionEntity<E>): CollectionModel<M> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}