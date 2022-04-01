package com.sdk.inplayer.mapper.notification

import android.util.Log
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.*
import com.sdk.notification.model.legacy.*
import com.sdk.notification.model.notification.*
import com.sdk.notification.model.notification.InPlayerAccessRevokedNotification

internal class NotificationMapper constructor(
    private val accessGrantedNotificationMapper: AccessGrantedNotificationMapper,
    private val accessRevokedNotificationMapper: AccessRevokedNotificationMapper,
    private val subscriptionSuccessMapper: SubscriptionSuccessMapper,
    private val paymentSuccessMapper: PaymentSuccessMapper,
    private val externalPaymentSuccessMapper: ExternalPaymentSuccessMapper,
    private val externalPaymentFailedMapper: ExternalPaymentFailedMapper,
    private val externalSubscriberCancelMapper: ExternalSubscriberCancelMapper
) : DomainMapper<InPlayerNotificationEntity, InPlayerNotification> {

    override fun mapFromDomain(inPlayerNotification: InPlayerNotificationEntity): InPlayerNotification {

        Log.d("Notif.mapFromDomain", "InPlayerNotificationEntity: --> ${inPlayerNotification.type}")

        if (inPlayerNotification is InPlayerAccessRevokedNotification) {
            return accessRevokedNotificationMapper.mapFromDomain(inPlayerNotification)
        }

        if (inPlayerNotification is InPlayerAccessGrantedNotificationEntity) {
            return accessGrantedNotificationMapper.mapFromDomain(inPlayerNotification)
        }

        if (inPlayerNotification is InPlayerAccountDeactivatedNotificationEntity) {
            return InPlayerAccountDeactivatedNotification(
                type = inPlayerNotification.type,
                timestamp = inPlayerNotification.timestamp
            )
        }

        if (inPlayerNotification is InPlayerAccountErasedNotificationEntity) {
            return InPlayerAccountErasedNotification(
                type = inPlayerNotification.type,
                timestamp = inPlayerNotification.timestamp
            )
        }

        if (inPlayerNotification is InPlayerAccountLogoutNotificationEntity) {
            return InPlayerAccountLogoutNotification(
                type = inPlayerNotification.type,
                timestamp = inPlayerNotification.timestamp
            )
        }

        // it was not handle
        if (inPlayerNotification is InPlayerSubscribeSuccessNotification) {
            return subscriptionSuccessMapper.mapFromDomain(inPlayerNotification)
        }

        // it was not handle
        if (inPlayerNotification is InPlayerPaymentCardSuccessNotifcation) {
            return paymentSuccessMapper.mapFromDomain(inPlayerNotification)
        }


        // it was not handle
        if (inPlayerNotification is InPlayerExternalPaymentSuccessNotification) {
            return externalPaymentSuccessMapper.mapFromDomain(inPlayerNotification)
        }

        // it was not handle
        if (inPlayerNotification is InPlayerExternalSubscriberCancelNotification) { // external.subscribe.cancel.success
            return externalSubscriberCancelMapper.mapFromDomain(inPlayerNotification)
        }

        // it was not handle EXTERNAL PAYMENT
        if (inPlayerNotification is InPlayerExternalPaymentFailedNotification) { //external.payment.failed
            var kco = externalPaymentFailedMapper.mapFromDomain(inPlayerNotification)
            Log.i("--kco--", "Message --> $kco");

            return kco
        }
        try {
            return InPlayerDefaultNotification(
                type = inPlayerNotification.type,
                timestamp = inPlayerNotification.timestamp
            )
        } catch (ex: Exception) {
            throw ClassCastException("NotificationMapper Unsupported mapping instance of class $inPlayerNotification")
        }


        throw ClassCastException("NotificationMapper Unsupported mapping instance of class $inPlayerNotification")
    }

    override fun mapToDomain(viewModel: InPlayerNotification): InPlayerNotificationEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}