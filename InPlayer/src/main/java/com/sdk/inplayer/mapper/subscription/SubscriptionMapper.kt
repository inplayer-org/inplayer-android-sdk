package com.sdk.inplayer.mapper.subscription

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.domain.entity.subscribtion.SubscriptionEntity
import com.sdk.inplayer.model.subscription.InPlayerSubscription

/**
 * Created by victor on 3/16/19
 */
internal class SubscriptionMapper : DomainMapper<SubscriptionEntity, InPlayerSubscription> {
    
    
    override fun mapFromDomain(entity: SubscriptionEntity): InPlayerSubscription {
        return InPlayerSubscription(amount = entity.amount,
                asset_id = entity.asset_id,
                asset_title = entity.asset_title,
                cancel_token = entity.cancel_token,
                created_at = entity.created_at,
                currency = entity.currency,
                description = entity.description,
                formatted_amount = entity.formatted_amount,
                merchant_id = entity.merchant_id,
                next_billing_date = entity.next_billing_date,
                status = entity.action_type?: "", // take the value for action type which is related for status
                unsubscribe_url = entity.unsubscribe_url,
                updated_at = entity.updated_at
        )
    }
    
    
    override fun mapToDomain(viewModel: InPlayerSubscription): SubscriptionEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}