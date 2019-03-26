package com.sdk.inplayer.mapper

import com.sdk.domain.entity.base.CollectionEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.InPlayerCollection

/**
 * Created by victor on 3/17/19
 */
class MapInPlayerCollection<E, I> constructor(private val collectionMapper: DomainMapper<E, I>)
    : DomainMapper<CollectionEntity<E>, InPlayerCollection<I>> {
    
    override fun mapFromDomain(domainEntity: CollectionEntity<E>): InPlayerCollection<I> {
        return InPlayerCollection(
                total = domainEntity.total,
                page = domainEntity.page,
                collection = domainEntity.collection.map { collectionMapper.mapFromDomain(it) },
                offset = domainEntity.offset,
                limit = domainEntity.limit
        )
    }
    
    override fun mapToDomain(viewModel: InPlayerCollection<I>): CollectionEntity<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}