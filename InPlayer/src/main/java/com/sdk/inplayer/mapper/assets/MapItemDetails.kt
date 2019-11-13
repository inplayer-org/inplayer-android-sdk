package com.sdk.inplayer.mapper.assets

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sdk.domain.entity.asset.ItemDetailsEntity
import com.sdk.domain.entity.asset.ItemMetadataEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.assets.InPlayerItem
import com.sdk.inplayer.model.assets.ItemMetadata
import java.lang.reflect.Type


internal class MapItemDetails constructor(
    private val mapAccessControlType: MapAccessControlType,
    private val mapItemType: MapItemType,
    private val mapAccessFee: MapAccessFee
) : DomainMapper<ItemDetailsEntity, InPlayerItem> {
    
    
    override fun mapFromDomain(domainEntity: ItemDetailsEntity): InPlayerItem {
        return InPlayerItem(
            id = domainEntity.id,
            merchantId = domainEntity.merchantId,
            merchantUUID = domainEntity.merchantUUID,
            isActive = domainEntity.isActive,
            title = domainEntity.title,
            accessControlType = mapAccessControlType.mapFromDomain(domainEntity.accessControlType),
            itemType = mapItemType.mapFromDomain(domainEntity.itemType),
            metahash = domainEntity.metahash,
            createdAt = domainEntity.createdAt,
            updatedAt = domainEntity.updatedAt,
            metadata = domainEntity.metadata.map { mapItemMetadata(it) },
            accessFees = domainEntity.accessFees.map { mapAccessFee.mapFromDomain(it) },
            content = parseContent(domainEntity.content, domainEntity.itemType.name)
        )
    }
    
    private fun parseContent(
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
    
    private fun mapItemMetadata(itemMetadata: ItemMetadataEntity): ItemMetadata {
        return ItemMetadata(
            id = itemMetadata.id,
            name = itemMetadata.name,
            value = itemMetadata.value
        )
    }
    
    override fun mapToDomain(viewModel: InPlayerItem): ItemDetailsEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                get() = object : TypeToken<InPlayerItem.InPlayerAsset.Wowza>(){}.type
        }
    }
    
    interface AssetContentParser{
        val item_type: String
        val contentClassType: Type
    }
}
