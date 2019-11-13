package com.sdk.inplayer.model.assets

class InPlayerItem(
    val id: Long,
    val merchantId: Long,
    val merchantUUID: String,
    val isActive: Boolean,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val accessFees: List<InPlayerAccessFee>,
    val accessControlType: InPlayerAccessControlType,
    val itemType: InPlayerItemType,
    val metahash: Map<String, String>,
    val metadata: List<ItemMetadata>,
    val content: InPlayerAsset?
) {
    sealed class InPlayerAsset {
        data class UnkownType(val value: String) : InPlayerAsset()
        data class Accedo(val video_key: String) :
            InPlayerAsset()
        
        data class Brightcove(val account_id: String, val video_id: String) :
            InPlayerAsset()
        
        data class Cloudfront(
            val player: String,
            val source_url: String
        ) : InPlayerAsset()
        
        data class DaCast(
            val token: String?,
            val content_id: String
        ) : InPlayerAsset()
        
        data class JwPlayer(
            val video_id: String,
            val player_id: String
        ) : InPlayerAsset()
        
        data class Laola(
            val video_id: String,
            val partner_id: String
        ) : InPlayerAsset()
        
        data class Kaltura(
            val entry_id: String,
            val uiconfig_id: String,
            val partner_id: String
        ) : InPlayerAsset()
        
        data class Livestream(
            val account_id: String,
            val event_id: String,
            val video_id: String
        ) : InPlayerAsset()
        
        data class Qbrick(
            val media_id: String,
            val account_id: String
        ) : InPlayerAsset()
        
        data class SportOne(
            val video_id: String
        ) : InPlayerAsset()
        
        data class SportRadar(
            val partner_id: String,
            val stream_id: String,
            val token: String?
        ) : InPlayerAsset()
        
        data class StreamAMG(
            val partner_id: String,
            val uiconfig_id: String,
            val entry_id: String,
            val token: String?
        ) : InPlayerAsset()
        
        data class Wistia(
            val video_id: String
        ) : InPlayerAsset()
        
        data class Wowza(
            val video_id: String
        ) : InPlayerAsset()
    }
}