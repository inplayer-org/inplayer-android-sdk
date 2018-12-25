package com.s.data.model.mapper

import com.s.data.model.InPlayerAccount
import com.s.domain.entity.AccountType
import com.s.domain.entity.InPlayerUser

/**
 * Created by victor on 12/25/18
 */
class MapInPlayerUser : ModelMapper<InPlayerAccount, InPlayerUser> {
    
    override fun mapFromModel(model: InPlayerAccount): InPlayerUser {
        
        //Creating the list for Account Type from the returned string
        val accountTypeList = mutableListOf<AccountType>()
        
        model.roles.forEach {
            accountTypeList.add(AccountType.valueOf(it))
        }
        
        return InPlayerUser(model.id, model.email, model.fullName, model.referrer, model.isCompleted, model.createdAt, model.updatedAt, accountTypeList)
    }
    
    override fun mapToModel(entity: InPlayerUser): InPlayerAccount {
        
        //Creating the list for Account Type from the returned string
        val accountTypeList = mutableListOf<String>()
        
        entity.roles.forEach {
            accountTypeList.add(it.toString())
        }
        
        return InPlayerAccount(entity.id, entity.email, entity.fullName, entity.referrer, accountTypeList, entity.isCompleted, entity.createdAt, entity.updatedAt)
    }
}