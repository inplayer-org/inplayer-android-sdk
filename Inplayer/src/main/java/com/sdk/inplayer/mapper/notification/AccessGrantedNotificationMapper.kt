package com.sdk.inplayer.mapper.notification

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.notification.INPAccessGrantedNotification
import com.sdk.inplayer.model.notification.INPAccessGrantedNotificationResource
import com.sdk.notification.model.notification.InPlayerAccessGrantedNotification
import com.sdk.notification.model.notification.InPlayerAccessGrantedNotificationResource

/**
 * Created by victor on 1/17/19
 */
internal class AccessGrantedNotificationMapper : DomainMapper<InPlayerAccessGrantedNotification, INPAccessGrantedNotification> {
    
    override fun mapFromDomain(notificationEntity: InPlayerAccessGrantedNotification): INPAccessGrantedNotification {
        return INPAccessGrantedNotification(mapResource(notifResource = notificationEntity.resource), notificationEntity.type, notificationEntity.timestamp)
    }
    
    override fun mapToDomain(viewModel: INPAccessGrantedNotification): InPlayerAccessGrantedNotification {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    private fun mapResource(notifResource: InPlayerAccessGrantedNotificationResource): INPAccessGrantedNotificationResource {
        return INPAccessGrantedNotificationResource(account_id = notifResource.account_id,
                country_code = notifResource.country_code,
                created_at = notifResource.created_at,
                customer_id = notifResource.customer_id,
                customer_uuid = notifResource.customer_uuid,
                expires_at = notifResource.expires_at,
                id = notifResource.id, ip_address = notifResource.ip_address,
                item = mapItem(notifResource.item),
                starts_at = notifResource.starts_at)
    }
    
    private fun mapItem(notifItem: InPlayerAccessGrantedNotificationResource.Item): INPAccessGrantedNotificationResource.Item {
        return INPAccessGrantedNotificationResource.Item(active = notifItem.active,
                content = notifItem.content,
                created_at = notifItem.created_at,
                id = notifItem.id, item_type = mapItemType(notifItem.item_type),
                merchant_id = notifItem.merchant_id,
                merchant_uuid = notifItem.merchant_uuid,
                metadata = mapMetaData(notifItem.metadata),
                title = notifItem.title,
                updated_at = notifItem.updated_at)
    }
    
    private fun mapItemType(notifItemType: InPlayerAccessGrantedNotificationResource.Item.ItemType)
            : INPAccessGrantedNotificationResource.Item.ItemType {
        return INPAccessGrantedNotificationResource.Item.ItemType(content_type = notifItemType.content_type,
                description = notifItemType.description,
                host = notifItemType.host,
                id = notifItemType.id,
                name = notifItemType.name)
    }
    
    private fun mapMetaData(notifMetadata: InPlayerAccessGrantedNotificationResource.Item.Metadata)
            : INPAccessGrantedNotificationResource.Item.Metadata {
        return INPAccessGrantedNotificationResource.Item.Metadata(paywall_cover_photo = notifMetadata.paywall_cover_photo,
                preview_button_label = notifMetadata.preview_button_label,
                preview_description = notifMetadata.preview_description,
                preview_title = notifMetadata.preview_title)
    }
    
    
}
