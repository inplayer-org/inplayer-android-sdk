package com.sdk.inplayer.mapper.payment

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.domain.entity.payment.CustomerAccessItemEntity
import com.sdk.inplayer.model.payment.InPlayerCustomerAccessItem

/**
 * Created by victor on 3/15/19
 */
class CustomerAccessItemMapper : DomainMapper<CustomerAccessItemEntity, InPlayerCustomerAccessItem> {
    
    override fun mapFromDomain(domainEntity: CustomerAccessItemEntity): InPlayerCustomerAccessItem {
        return InPlayerCustomerAccessItem(
                consumer_email = domainEntity.consumer_email,
                created_at = domainEntity.created_at,
                customer_id = domainEntity.customer_id,
                expires_at = domainEntity.expires_at,
                is_trial = domainEntity.is_trial,
                item_access_id = domainEntity.item_access_id,
                item_id = domainEntity.item_id,
                item_title = domainEntity.item_title,
                merchant_id = domainEntity.merchant_id,
                parent_resource_id = domainEntity.parent_resource_id,
                payment_method = domainEntity.payment_method,
                payment_tool = domainEntity.payment_tool,
                purchased_access_fee_description = domainEntity.purchased_access_fee_description,
                purchased_access_fee_id = domainEntity.purchased_access_fee_id,
                purchased_access_fee_type = domainEntity.purchased_access_fee_type,
                purchased_amount = domainEntity.purchased_amount,
                purchased_currency = domainEntity.purchased_currency,
                revoked = domainEntity.revoked,
                starts_at = domainEntity.starts_at,
                type = domainEntity.type
        )
    }
    
    override fun mapToDomain(viewModel: InPlayerCustomerAccessItem): CustomerAccessItemEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}