package com.s.inplayer.mapper.notification

import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.notification.InPlayerAccountDeactivatedNotification
import com.s.inplayer.model.notification.InPlayerAccountErasedNotification
import com.s.inplayer.model.notification.InPlayerAccountLogoutNotification
import com.s.inplayer.model.notification.InPlayerNotification
import com.s.notification.model.notification.*

/**
 * Created by victor on 1/17/19
 */
class NotificationMapper constructor(val accessGrantedNotificationMapper: AccessGrantedNotificationMapper,
                                     val accessRevokedNotificationMapper: AccessRevokedNotificationMapper) : DomainMapper<InPlayerNotificationEntity, InPlayerNotification> {
    
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