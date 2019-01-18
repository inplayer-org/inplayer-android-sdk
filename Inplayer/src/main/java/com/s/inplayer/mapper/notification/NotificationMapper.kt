package com.s.inplayer.mapper.notification

import com.s.domain.entity.mapper.DomainMapper
import com.s.inplayer.model.notification.INPAccountDeactivatedNotification
import com.s.inplayer.model.notification.INPAccountErasedNotification
import com.s.inplayer.model.notification.INPAccountLogoutNotification
import com.s.inplayer.model.notification.INPNotification
import com.s.notification.model.notification.*

/**
 * Created by victor on 1/17/19
 */
class NotificationMapper constructor(val accessGrantedNotificationMapper: AccessGrantedNotificationMapper,
                                     val accessRevokedNotificationMapper: AccessRevokedNotificationMapper) : DomainMapper<InPlayerNotification, INPNotification> {
    
    override fun mapFromDomain(inPlayerNotification: InPlayerNotification): INPNotification {
        
        if (inPlayerNotification is InPlayerAccessRevokedNotification) {
            return accessRevokedNotificationMapper.mapFromDomain(inPlayerNotification)
        }
        
        if (inPlayerNotification is InPlayerAccessGrantedNotification) {
            return accessGrantedNotificationMapper.mapFromDomain(inPlayerNotification)
        }
        
        if (inPlayerNotification is InPlayerAccountDeactivatedNotification) {
            return INPAccountDeactivatedNotification(type = inPlayerNotification.type,
                    timestamp = inPlayerNotification.timestamp)
        }
        
        if (inPlayerNotification is InPlayerAccountErasedNotification) {
            return INPAccountErasedNotification(type = inPlayerNotification.type,
                    timestamp = inPlayerNotification.timestamp)
        }
        
        if (inPlayerNotification is InPlayerAccountLogoutNotification) {
            return INPAccountLogoutNotification(type = inPlayerNotification.type,
                    timestamp = inPlayerNotification.timestamp)
        }
        
        throw ClassCastException("NotificationMapper Unsupported mapping instance of class $inPlayerNotification")
    }
    
    override fun mapToDomain(viewModel: INPNotification): InPlayerNotification {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}