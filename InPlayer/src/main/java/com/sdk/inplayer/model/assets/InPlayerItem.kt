package com.sdk.inplayer.model.assets

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sdk.domain.entity.asset.ItemDetailsEntity
import java.lang.reflect.Type

class InPlayerItem(
    val id: Long,
    val merchantId: Long,
    val merchantUUID: String,
    val isActive: Boolean,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val accessFees: List<InPlayerAccessFee>,
    val accessControlType: InPlayerAccessControlType?,
    val itemType: InPlayerItemType?,
    val metahash: Map<String, String>,
    val metadata: List<ItemMetadata>,
    val content: InPlayerAsset?
) {
    constructor(model: ItemDetailsEntity) : this(
        id = model.id,
        merchantId = model.merchantId,
        merchantUUID = model.merchantUUID,
        isActive = model.isActive,
        title = model.title,
        accessControlType = model.accessControlType?.let { InPlayerAccessControlType(it) },
        itemType = model.itemType?.let { InPlayerItemType(it) },
        metahash = model.metahash,
        createdAt = model.createdAt,
        updatedAt = model.updatedAt,
        metadata = model.metadata.map { ItemMetadata(it) },
        accessFees = model.accessFees.map { InPlayerAccessFee(it) },
        content = parseContent(model.content, model.itemType?.name ?: "")
    )
    
    companion object{
        fun parseContent(
            contentString: String?,
            type: String
        ): InPlayerItem.InPlayerAsset? {
            if (contentString == null)
                return null
            AssetContent.values().forEach {
                if(it.item_type == type)
                    return Gson().fromJson(contentString, it.contentClassType)
            }
            return InPlayerItem.InPlayerAsset.UnkownType("$contentString")
        }
    }
    
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
    
    enum class AssetContent: AssetContentParser{
        Accedo{
            override val item_type: String
                get() = "accedo_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Accedo>(){}.type
        },
        Brightcove{
            override val item_type: String
                get() = "brigthcove_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Brightcove>(){}.type
            
        },
        Cloudfront{
            override val item_type: String
                get() = "cloudfront_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Cloudfront>(){}.type
            
        },
        DaCast{
            override val item_type: String
                get() = "dacast_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.DaCast>(){}.type
            
        },
        JwPlayer{
            override val item_type: String
                get() = "jw_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.JwPlayer>(){}.type
        },
        Laola{
            override val item_type: String
                get() = "laola_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Laola>(){}.type
        },
        Kaltura{
            override val item_type: String
                get() = "kaltura_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Kaltura>(){}.type
        },
        Livestream{
            override val item_type: String
                get() = "livestream_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Livestream>(){}.type
        },
        Qbrick{
            override val item_type: String
                get() = "qbrick_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Qbrick>(){}.type
        },
        SportOne{
            override val item_type: String
                get() = "item_type"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.SportOne>(){}.type
        },
        SportRadar{
            override val item_type: String
                get() = "sportradar_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.SportRadar>(){}.type
        },
        StreamAMG{
            override val item_type: String
                get() = "stramamg_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.StreamAMG>(){}.type
        },
        Wistia{
            override val item_type: String
                get() = "wistia_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Wistia>(){}.type
        },
        Wowza{
            override val item_type: String
                get() = "wowza_asset"
            override val contentClassType: Type
                get() = object : TypeToken<InPlayerAsset.Wowza>(){}.type
        }
    }
    
    interface AssetContentParser{
        val item_type: String
        val contentClassType: Type
    }
}