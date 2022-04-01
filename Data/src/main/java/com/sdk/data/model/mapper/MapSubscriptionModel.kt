package com.sdk.data.model.mapper

import com.sdk.data.model.subscription.SubscriptionModel
import com.sdk.domain.entity.subscribtion.SubscriptionEntity

/**
 * Created by victor on 3/16/19
 */
class MapSubscriptionModel : ModelMapper<SubscriptionModel, SubscriptionEntity> {

    override fun mapFromModel(model: SubscriptionModel): SubscriptionEntity {
        return SubscriptionEntity(
            amount = model.amount,
            asset_id = model.item_id,
            asset_title = model.item_title ?: "",
            cancel_token = model.cancel_token ?: "",
            created_at = model.created_at,
            currency = model.currency ?: "",
            description = model.description ?: "",
            formatted_amount = model.formatted_amount ?: "",
            merchant_id = model.merchant_id,
            next_billing_date = model.next_rebill_date,
            status = model.status ?: "",
            action_type = model.action_type ?: "", // take the value for action type which is related for status
            unsubscribe_url = model.unsubscribe_url ?: "",
            updated_at = model.updated_at
        )
    }

    override fun mapToModel(entity: SubscriptionEntity): SubscriptionModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}