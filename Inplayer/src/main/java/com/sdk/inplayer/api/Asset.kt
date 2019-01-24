package com.sdk.inplayer.api

import android.annotation.SuppressLint
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.assets.GetAccessFeesUseCase
import com.sdk.domain.usecase.assets.GetItemAccessUseCase
import com.sdk.domain.usecase.assets.GetItemDetailsUseCase
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.mapper.assets.MapAccessFee
import com.sdk.inplayer.mapper.assets.MapItemAccess
import com.sdk.inplayer.mapper.assets.MapItemDetails
import com.sdk.inplayer.model.assets.InPlayerAccessFee
import com.sdk.inplayer.model.assets.InPlayerItem
import com.sdk.inplayer.model.assets.InPlayerItemAccess
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.service.AssetService
import com.sdk.inplayer.util.InPlayerSDKConfiguration

/**
 * In this section, you can find all the necessary endpoints for creating
 * an asset and partaking in any operation regarding the asset resource.
 */
@SuppressLint("CheckResult")
class Asset internal constructor(private val appSchedulers: InPlayerSchedulers,
                                 private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
                                 private val assetService: AssetService,
                                 private val mapItemDetails: MapItemDetails,
                                 private val mapAccessFee: MapAccessFee,
                                 private val mapItemAccess: MapItemAccess) {
    
    
    /**
     * Returns details about the item type, title, state, when and how it was purchased etc.
     *
     *
     * @param id Int Item ID
     * @param callback InPlayerCallback<InPlayerItem, InPlayerException>
     */
    fun getItemDetails(id: Int, callback: InPlayerCallback<InPlayerItem, InPlayerException>) {
        assetService.getItemDetailsUseCase.execute(GetItemDetailsUseCase.Params(id, inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(mapItemDetails.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Lists the various fees for a specific asset.
     *
     * @param id Int Item ID
     * @param callback InPlayerCallback<List<InPlayerAccessFee>, InPlayerException>
     */
    fun getAccessFees(id: Int, callback: InPlayerCallback<List<InPlayerAccessFee>, InPlayerException>) {
        assetService.getAccessFeesUseCase.execute(GetAccessFeesUseCase.Params(id))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({ list ->
                    callback.done(list.map { mapAccessFee.mapFromDomain(it) }, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    /**
     * Checks and retrieves the customer’s entitlement to an asset.
     *
     *
     * @param id Int Item ID
     * @param callback InPlayerCallback<InPlayerItemAccess, InPlayerException>
     */
    fun getItemAccess(id: Int, callback: InPlayerCallback<InPlayerItemAccess, InPlayerException>) {
        assetService.getItemAccessUseCase.execute(GetItemAccessUseCase.Params(id))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(mapItemAccess.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}