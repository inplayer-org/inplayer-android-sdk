package com.sdk.inplayer.mapper.notification

import android.util.Log
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.*
import com.sdk.notification.model.notification.*
import com.sdk.notification.model.notification.InPlayerAccessRevokedNotification

internal class NotificationMapper constructor(
    private val accessGrantedNotificationMapper: AccessGrantedNotificationMapper,
    private val accessRevokedNotificationMapper: AccessRevokedNotificationMapper
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

        try {
            return InPlayerDefaultNotification(
                type = inPlayerNotification.type,
                timestamp = inPlayerNotification.timestamp
            )
        } catch (ex: Exception) {
            throw ClassCastException("NotificationMapper Unsupported mapping instance of class $inPlayerNotification")
        }

//        // it was not handle
//        if (inPlayerNotification is InPlayerSubscribeSuccessNotification) {
//            return subscriptionSuccessMapper.mapFromDomain(inPlayerNotification)
//        }
//
//        // it was not handle
//        if (inPlayerNotification is InPlayerPaymentCardSuccessNotifcation) {
//            return paymentSuccessMapper.mapFromDomain(inPlayerNotification)
//        }

        throw ClassCastException("NotificationMapper Unsupported mapping instance of class $inPlayerNotification")
    }

    override fun mapToDomain(viewModel: InPlayerNotification): InPlayerNotificationEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}