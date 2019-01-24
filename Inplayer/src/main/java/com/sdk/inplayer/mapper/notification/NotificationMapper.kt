package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.InPlayerAccountDeactivatedNotification
import com.sdk.inplayer.model.notification.InPlayerAccountErasedNotification
import com.sdk.inplayer.model.notification.InPlayerAccountLogoutNotification
import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.notification.model.notification.*


internal class NotificationMapper constructor(private val accessGrantedNotificationMapper: AccessGrantedNotificationMapper,
                                              private val accessRevokedNotificationMapper: AccessRevokedNotificationMapper) : DomainMapper<InPlayerNotificationEntity, InPlayerNotification> {
    
    override fun mapFromDomain(inPlayerNotification: InPlayerNotificationEntity): InPlayerNotification {
        
        if (inPlayerNotification is InPlayerAccessRevokedNotification) {
            return accessRevokedNotificationMapper.mapFromDomain(inPlayerNotification)
        }
        
        if (inPlayerNotification is InPlayerAccessGrantedNotification) {
            return accessGrantedNotificationMapper.mapFromDomain(inPlayerNotification)
        }
        
        if (inPlayerNotification is InPlayerAccountDeactivatedNotificationEntity) {
            return InPlayerAccountDeactivatedNotification(type = inPlayerNotification.type,
                    timestamp = inPlayerNotification.timestamp)
        }
        
        if (inPlayerNotification is InPlayerAccountErasedNotificationEntity) {
            return InPlayerAccountErasedNotification(type = inPlayerNotification.type,
                    timestamp = inPlayerNotification.timestamp)
        }
        
        if (inPlayerNotification is InPlayerAccountLogoutNotificationEntity) {
            return InPlayerAccountLogoutNotification(type = inPlayerNotification.type,
                    timestamp = inPlayerNotification.timestamp)
        }
        
        throw ClassCastException("NotificationMapper Unsupported mapping instance of class $inPlayerNotification")
    }
    
    override fun mapToDomain(viewModel: InPlayerNotification): InPlayerNotificationEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}