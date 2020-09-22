package com.sdk.data.model.mapper

import com.sdk.data.model.payment.CustomerAccessItemModel
import com.sdk.domain.entity.payment.CustomerAccessItemEntity

/**
 * Created by victor on 3/13/19
 */
class MapCustomerAccessItem : ModelMapper<CustomerAccessItemModel, CustomerAccessItemEntity> {
    
    override fun mapFromModel(model: CustomerAccessItemModel): CustomerAccessItemEntity {
        return  CustomerAccessItemEntity(
                consumer_email = model.consumer_email,
                created_at = model.created_at,
                customer_id = model.customer_id,
                expires_at = model.expires_at,
                is_trial = model.is_trial,
                item_access_id = model.item_access_id,
                item_id = model.item_id,
                item_title = model.item_title,
                merchant_id = model.merchant_id,
                parent_resource_id = model.parent_resource_id,
                payment_method = model.payment_method,
                payment_tool = model.payment_tool,
                purchased_access_fee_description = model.purchased_access_fee_description,
                purchased_access_fee_id = model.purchased_access_fee_id,
                purchased_access_fee_type = model.purchased_access_fee_type,
                purchased_amount = model.purchased_amount,
                purchased_currency = model.purchased_currency,
                revoked = model.revoked,
                starts_at = model.starts_at.toLongOrNull()?:0,
                type = model.type
        )
    }
    
    override fun mapToModel(entity: CustomerAccessItemEntity): CustomerAccessItemModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}