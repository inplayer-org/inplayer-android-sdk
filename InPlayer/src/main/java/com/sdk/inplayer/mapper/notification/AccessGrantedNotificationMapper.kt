package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotification
import com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotificationResource
import com.sdk.notification.model.notification.InPlayerAccessGrantedNotificationEntity


internal class AccessGrantedNotificationMapper : DomainMapper<InPlayerAccessGrantedNotificationEntity,com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotification> {
 
    override fun mapFromDomain(notificationEntity: InPlayerAccessGrantedNotificationEntity): com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotification {
        return com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotification(mapResource(notifResource = notificationEntity.resource), notificationEntity.type, notificationEntity.timestamp)
    }

    override fun mapToDomain(viewModel: com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotification): InPlayerAccessGrantedNotificationEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    private fun mapResource(notifResource: com.sdk.notification.model.notification.InPlayerAccessGrantedNotificationResource): com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotificationResource {
        return com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotificationResource(account_id = notifResource.account_id,
                country_code = notifResource.country_code,
                created_at = notifResource.created_at,
                customer_id = notifResource.customer_id,
                customer_uuid = notifResource.customer_uuid,
                expires_at = notifResource.expires_at,
                id = notifResource.id, ip_address = notifResource.ip_address,
                item = mapItem(notifResource.item),
                starts_at = notifResource.starts_at)
    }
    
    private fun mapItem(notifItem: com.sdk.notification.model.notification.InPlayerAccessGrantedNotificationResource.Item): InPlayerAccessGrantedNotificationResource.Item {
        return InPlayerAccessGrantedNotificationResource.Item(active = notifItem.active,
                content = notifItem.content,
                created_at = notifItem.created_at,
                id = notifItem.id, item_type = mapItemType(notifItem.item_type),
                merchant_id = notifItem.merchant_id,
                merchant_uuid = notifItem.merchant_uuid,
                metahash = mapMetaData(notifItem.metahash),
                title = notifItem.title,
                updated_at = notifItem.updated_at)
    }
    
    private fun mapItemType(notifItemType: com.sdk.notification.model.notification.InPlayerAccessGrantedNotificationResource.Item.ItemType)
            : com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotificationResource.Item.ItemType {
        return InPlayerAccessGrantedNotificationResource.Item.ItemType(content_type = notifItemType.content_type,
                description = notifItemType.description,
                host = notifItemType.host,
                id = notifItemType.id,
                name = notifItemType.name)
    }
    
    private fun mapMetaData(notifMetadata: com.sdk.notification.model.notification.InPlayerAccessGrantedNotificationResource.Item.Metadata)
            : com.sdk.inplayer.model.notification.InPlayerAccessGrantedNotificationResource.Item.Metadata {
        return InPlayerAccessGrantedNotificationResource.Item.Metadata(paywall_cover_photo = notifMetadata.paywall_cover_photo,
                preview_button_label = notifMetadata.preview_button_label,
                preview_description = notifMetadata.preview_description,
                preview_title = notifMetadata.preview_title)
    }
    
    
}
