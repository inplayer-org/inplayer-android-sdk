package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.INPAccessRevokedNotification
import com.sdk.inplayer.model.notification.INPAccessRevokedNotificationResource
import com.sdk.notification.model.notification.InPlayerAccessRevokedNotification

/**
 * Created by victor on 1/17/19
 */
internal class AccessRevokedNotificationMapper : DomainMapper<InPlayerAccessRevokedNotification, INPAccessRevokedNotification> {
    
    override fun mapFromDomain(notifEntity: InPlayerAccessRevokedNotification): INPAccessRevokedNotification {
        return INPAccessRevokedNotification(resource = INPAccessRevokedNotificationResource(notifEntity.resource.item_id),
                type = notifEntity.type,
                timestamp = notifEntity.timestamp)
    }
    
    override fun mapToDomain(viewModel: INPAccessRevokedNotification): InPlayerAccessRevokedNotification {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}