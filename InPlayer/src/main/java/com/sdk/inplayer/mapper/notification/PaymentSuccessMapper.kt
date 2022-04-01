package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.mapper.payment.InPlayerPaymentSuccessNotification
import com.sdk.notification.model.legacy.InPlayerPaymentCardSuccessNotifcation
import com.sdk.notification.model.legacy.InPlayerPaymentCardSuccessNotificationResource


internal class PaymentSuccessMapper :
    DomainMapper<InPlayerPaymentCardSuccessNotifcation, InPlayerPaymentSuccessNotification> {

    override fun mapFromDomain(domainEntity: InPlayerPaymentCardSuccessNotifcation): InPlayerPaymentSuccessNotification {
        return InPlayerPaymentSuccessNotification(
            resource = mapResource(domainEntity.resource),
            type = domainEntity.type,
            timestamp = domainEntity.timestamp
        )
    }

    private fun mapResource(resource: InPlayerPaymentCardSuccessNotificationResource): InPlayerPaymentCardSuccessNotificationResource {
        return InPlayerPaymentCardSuccessNotificationResource(
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

    override fun mapToDomain(viewModel: InPlayerPaymentSuccessNotification): InPlayerPaymentCardSuccessNotifcation {
        TODO("Not yet implemented")
    }

}