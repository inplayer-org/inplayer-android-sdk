package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.mapper.subscription.InPlayerSubscriptionSuccessNotification
import com.sdk.notification.model.legacy.InPlayerSubscribeSuccessNotification
import com.sdk.notification.model.legacy.InPlayerSubscribeSuccessNotificationResource


internal class SubscriptionSuccessMapper :
    DomainMapper<InPlayerSubscribeSuccessNotification, InPlayerSubscriptionSuccessNotification> {

    override fun mapFromDomain(domainEntity: InPlayerSubscribeSuccessNotification): InPlayerSubscriptionSuccessNotification {
        return InPlayerSubscriptionSuccessNotification(
            resource = mapResource(domainEntity.resource),
            type = domainEntity.type,
            timestamp = domainEntity.timestamp
        )
    }

    private fun mapResource(resource: InPlayerSubscribeSuccessNotificationResource): InPlayerSubscribeSuccessNotificationResource {
        return InPlayerSubscribeSuccessNotificationResource(
            access_fee_id = resource.access_fee_id,
            amount = resource.amount ?: "0",
            code = resource.code,
            currency_iso = resource.currency_iso ?: "",
            customer_id = resource.customer_id,
            description = resource.description ?: "",
            email = resource.email ?: "",
            formatted_amount = resource.formatted_amount ?: "",
            status = resource.status ?: "",
            timestamp = resource.timestamp,
            transaction = resource.transaction ?: ""
        )
    }

    override fun mapToDomain(viewModel: InPlayerSubscriptionSuccessNotification): InPlayerSubscribeSuccessNotification {
        TODO("Not yet implemented")
    }

}