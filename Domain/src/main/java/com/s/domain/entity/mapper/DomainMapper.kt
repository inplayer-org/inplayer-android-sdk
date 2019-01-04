package com.s.domain.entity.mapper

/**
 * Created by victor on 1/4/19
 */
interface DomainMapper<E, I> {
    
    fun mapFromDomain(domainEntity: E): I
    
    fun mapToDomain(viewModel: I): E
}
