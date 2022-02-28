package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.mapper.payment.InPlayerExternalPaymentFailedMapper
import com.sdk.notification.model.legacy.InPlayerExternalPaymentFailedNotification
import com.sdk.notification.model.legacy.InPlayerExternalPaymentFailedNotificationResource


internal class ExternalPaymentFailedMapper :
    DomainMapper<InPlayerExternalPaymentFailedNotification, InPlayerExternalPaymentFailedMapper> {

    override fun mapFromDomain(domainEntity: InPlayerExternalPaymentFailedNotification): InPlayerExternalPaymentFailedMapper {
        return InPlayerExternalPaymentFailedMapper(
            resource = mapResource(domainEntity.resource),
            type = domainEntity.type,
            timestamp = domainEntity.timestamp
        )
    }

    private fun mapResource(resource: InPlayerExternalPaymentFailedNotificationResource): InPlayerExternalPaymentFailedNotificationResource {
        return InPlayerExternalPaymentFailedNotificationResource(
            code = resource.code,
            explain = resource.explain ?: "",
            message = resource.message ?: ""
        )
    }

    override fun mapToDomain(viewModel: InPlayerExternalPaymentFailedMapper): InPlayerExternalPaymentFailedNotification {
        TODO("Not yet implemented")
    }


}