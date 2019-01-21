package com.s.notification.model.notification

data class InPlayerAccessGrantedNotification(
        val resource: InPlayerAccessGrantedNotificationResource,
        override val type: String, override val timestamp: Long
) : InPlayerNotificationEntity

data class InPlayerAccessGrantedNotificationResource(
        val account_id: Int,
        val country_code: String,
        val created_at: Int,
        val customer_id: Int,
        val customer_uuid: String,
        val expires_at: Int,
        val id: Int,
        val ip_address: String,
        val item: Item,
        val starts_at: Int
) {
    data class Item(
            val active: Boolean,
            val content: String,
            val created_at: Int,
            val id: Int,
            val item_type: ItemType,
            val merchant_id: Int,
            val merchant_uuid: String,
            val metadata: Metadata,
            val title: String,
            val updated_at: Int
    ) {
        data class ItemType(
                val content_type: String,
                val description: String,
                val host: String,
                val id: Int,
                val name: String
        )
        
        data class Metadata(
                val paywall_cover_photo: String,
                val preview_button_label: String,
                val preview_description: String,
                val preview_title: String
        )
    }
}