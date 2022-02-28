package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.mapper.payment.InPlayerExternalSubscriberCancelMapper
import com.sdk.notification.model.legacy.InPlayerExternalSubscriberCancelNotification
import com.sdk.notification.model.legacy.InPlayerExternalSubscriberCancelNotificationResource

internal class ExternalSubscriberCancelMapper :
    DomainMapper<InPlayerExternalSubscriberCancelNotification, InPlayerExternalSubscriberCancelMapper> {

    override fun mapFromDomain(domainEntity: InPlayerExternalSubscriberCancelNotification): InPlayerExternalSubscriberCancelMapper {
        return InPlayerExternalSubscriberCancelMapper(
            resource = mapResource(domainEntity.resource),
            type = domainEntity.type,
            timestamp = domainEntity.timestamp
        )
    }

    private fun mapResource(resource: InPlayerExternalSubscriberCancelNotificationResource): InPlayerExternalSubscriberCancelNotificationResource {
        return InPlayerExternalSubscriberCancelNotificationResource(
            access_fee_id = resource.access_fee_id,
            amount = resource.amount ?: "0",
            code = resource.code,
            currency_iso = resource.currency_iso ?: "",
            customer_id = resource.customer_id,
            description = resource.description ?: "",
            discount_percent = resource.discount_percent,
            email = resource.email ?: "",
            formatted_amount = resource.formatted_amount ?: "",
            full_amount = resource.full_amount ?: "",
            item_id = resource.item_id,
            next_rebill_date = resource.next_rebill_date,
            payment_method = resource.payment_method ?: "",
            previewTitle = resource.previewTitle ?: "",
            status = resource.status ?: "",
            timestamp = resource.timestamp,
            transaction = resource.transaction ?: "",
            voucher_code = resource.voucher_code ?: ""
        )
    }

    override fun mapToDomain(viewModel: InPlayerExternalSubscriberCancelMapper): InPlayerExternalSubscriberCancelNotification {
        TODO("Not yet implemented")
    }


}