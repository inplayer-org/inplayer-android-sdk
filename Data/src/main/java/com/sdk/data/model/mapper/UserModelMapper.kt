package com.sdk.data.model.mapper

import com.sdk.data.model.account.InPlayerAccount
import com.sdk.domain.entity.account.AccountType
import com.sdk.domain.entity.account.InPlayerDomainUser


class UserModelMapper : ModelMapper<InPlayerAccount, InPlayerDomainUser> {
    
    override fun mapFromModel(model: InPlayerAccount): InPlayerDomainUser {
        
        //Creating the list for Account Type from the returned string
        val accountTypeList = mutableListOf<AccountType>()
        
        return InPlayerDomainUser(
                id = model.id,
                email = model.email,
                fullName = model.fullName,
                referrer = model.referrer,
                isCompleted = model.isCompleted,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt,
                roles = model.roles,
                metadata = model.metadata,
                merchantId = model.merchantId,
                merchantUUID = model.merchantUUID,
                username = model.username,
                uuid = model.uuid)
    }
    
    override fun mapToModel(entity: InPlayerDomainUser): InPlayerAccount {
        
        //Creating the list for Account Type from the returned string
        val accountTypeList = mutableListOf<String>()
        
        entity.roles.forEach {
            accountTypeList.add(it.toString())
        }
        
        return InPlayerAccount(id = entity.id,
                email = entity.email,
                fullName = entity.fullName,
                referrer = entity.referrer,
                roles = accountTypeList,
                isCompleted = entity.isCompleted,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt,
                metadata = entity.metadata,
                merchantId = entity.merchantId,
                merchantUUID = entity.merchantUUID,
                username = entity.username,
                uuid = entity.uuid)
    }
}