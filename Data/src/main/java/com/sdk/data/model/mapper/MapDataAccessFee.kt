package com.sdk.data.model.mapper

import com.sdk.data.model.asset.AccessFeeModel
import com.sdk.data.model.asset.ExternalFeesApiModel
import com.sdk.data.model.asset.GeoRestrictionApiModel
import com.sdk.data.model.asset.SeasonalFeeApiModel
import com.sdk.domain.entity.asset.AccessFeeEntity
import com.sdk.domain.entity.asset.ExternalFeesEntity
import com.sdk.domain.entity.asset.GeoRestrictionEntity
import com.sdk.domain.entity.asset.SeasonalFeeEntity

class MapDataAccessFee constructor(private val mapAccessType: MapDataAccessType, private val mapItemType: MapDataItemType,
                                   private val mapTrialPeriod: MapDataTrialPeriod, private val mapSetupFee: MapDataSetupFee) : ModelMapper<AccessFeeModel, AccessFeeEntity> {
    
    override fun mapFromModel(model: AccessFeeModel): AccessFeeEntity {
        
        return AccessFeeEntity(id = model.id,
                merchantId = model.merchantId,
                amount = model.amount,
                currency = model.currency ?: "",
                description = model.description ?: "",
                accessTypeEntity = mapAccessType.mapFromModel(model.accessTypeModel),
                itemType = model.itemType ?: "",
                trialPeriodEntity = model.trialPeriodModel?.let { mapTrialPeriod.mapFromModel(it) },
                setupFeeEntity = model.setupFeeModel?.let { mapSetupFee.mapFromModel(it) },
                expiresAt = model.expiresAt,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt,
                startsAt = model.startsAt,
                geoRestrictionEntity = model.geoRestrictionApiModel?.let { mapGeoRestrictionEntity(it) },
                seasonalFeeEntity = model.seasonalFeeApiModel?.let { mapSeasonalFeeEntity(it) },
                externalFeesEntity = model.externalFees?.let { mapExternalFeesEntity(it) },
                item = null)
    }
    
    override fun mapToModel(entity: AccessFeeEntity): AccessFeeModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    fun mapGeoRestrictionEntity(geoRestrictionApiModel: GeoRestrictionApiModel) =
        GeoRestrictionEntity(
            id = geoRestrictionApiModel.id,
            country_iso = geoRestrictionApiModel.country_iso,
            country_set_id = geoRestrictionApiModel.country_set_id,
            type = geoRestrictionApiModel.type
        )
    
    fun mapSeasonalFeeEntity(seasonalFeeApiModel: SeasonalFeeApiModel) =
        SeasonalFeeEntity(
            offSeasonAccess = seasonalFeeApiModel.offSeasonAccess,
            currentPriceAmount = seasonalFeeApiModel.currentPriceAmount,
            anchorDate = seasonalFeeApiModel.anchorDate
        )
    
    fun mapExternalFeesEntity(externalFeesApiModel: ExternalFeesApiModel) =
        ExternalFeesEntity(
            id = externalFeesApiModel.id,
            paymentProviderId = externalFeesApiModel.paymentProviderId,
            accessFeeId = externalFeesApiModel.accessFeeId,
            externalId = externalFeesApiModel.externalId,
            merchantId = externalFeesApiModel.merchantId
        )
}