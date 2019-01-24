package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.InPlayerAccessRevokedNotificationResource
import com.sdk.notification.model.notification.InPlayerAccessRevokedNotification


internal class AccessRevokedNotificationMapper : DomainMapper<InPlayerAccessRevokedNotification, com.sdk.inplayer.model.notification.InPlayerAccessRevokedNotification> {
    
    override fun mapFromDomain(notifEntity: InPlayerAccessRevokedNotification): com.sdk.inplayer.model.notification.InPlayerAccessRevokedNotification {
        return com.sdk.inplayer.model.notification.InPlayerAccessRevokedNotification(resource = InPlayerAccessRevokedNotificationResource(notifEntity.resource.item_id),
                type = notifEntity.type,
                timestamp = notifEntity.timestamp)
    }
    
    override fun mapToDomain(viewModel: com.sdk.inplayer.model.notification.InPlayerAccessRevokedNotification): InPlayerAccessRevokedNotification {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}