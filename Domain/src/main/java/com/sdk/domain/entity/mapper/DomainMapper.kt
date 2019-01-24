package com.sdk.domain.entity.mapper


interface DomainMapper<E, I> {
    
    fun mapFromDomain(domainEntity: E): I
    
    fun mapToDomain(viewModel: I): E
}
