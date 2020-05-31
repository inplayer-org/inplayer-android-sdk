package com.sdk.inplayer.api

import android.annotation.SuppressLint
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.assets.GetAccessFeesUseCase
import com.sdk.domain.usecase.assets.GetItemAccessUseCase
import com.sdk.domain.usecase.assets.GetItemDetailsUseCase
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
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
class Asset internal constructor(
    private val appSchedulers: InPlayerSchedulers,
    private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
    private val assetService: AssetService
) {
    
    /**
     * Returns details about the item type, title, state, when and how it was purchased etc.
     *
     *
     * @param id Int Item ID
     * @param callback InPlayerCallback<InPlayerItem, InPlayerException>
     */
    fun getAsset(id: Int, callback: InPlayerCallback<InPlayerItem, InPlayerException>) {
        assetService.getItemDetailsUseCase.execute(
            GetItemDetailsUseCase.Params.ItemDetailsParams(
                id,
                inPlayerSDKConfiguration.merchantUUID
            )
        )
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(InPlayerItem(it), null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    /**
     * Get an external assets info
     *
     *
     * @param assetType String The type ID of the asset
     * @param externalId String The ID of the external asset
     * @param merchantUUID String The merchant uuid
     * @param callback InPlayerCallback<InPlayerItem, InPlayerException>
     */
    fun getExternalAsset(assetType: String, externalId: String, merchantUUID: String, callback: InPlayerCallback<InPlayerItem, InPlayerException>) {
        assetService.getItemDetailsUseCase.execute(
            GetItemDetailsUseCase.Params.ExternalAssetParams(
                assetType = assetType,
                externalId = externalId,
                merchantUUID = merchantUUID
            )
        ).subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(InPlayerItem(it), null)
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
    fun getAssetAccessFees(
        id: Int,
        callback: InPlayerCallback<List<InPlayerAccessFee>, InPlayerException>
    ) {
        assetService.getAccessFeesUseCase.execute(GetAccessFeesUseCase.Params.V1(id))
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({ list ->
                callback.done(list.map { InPlayerAccessFee(it) }, null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    
    /**
     * Lists the various fees for a specific asset v2.
     *
     * @param id Int Item ID
     * @param voucherId Int Voucher
     * @param callback InPlayerCallback<List<InPlayerAccessFee>, InPlayerException>
     */
    fun getAssetAccessFeesv2(
        id: Int,
        voucherId: Int? = null,
        callback: InPlayerCallback<List<InPlayerAccessFee>, InPlayerException>
    ) {
        assetService.getAccessFeesUseCase.execute(GetAccessFeesUseCase.Params.V2(id, voucherId))
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({ list ->
                callback.done(list.map { InPlayerAccessFee(it) }, null)
            }, {
                callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
            })
    }
    
    fun getAssetAccessFeesv2(id: Int, callback: InPlayerCallback<List<InPlayerAccessFee>, InPlayerException>) {
        getAssetAccessFeesv2(id, null, callback)
    }
    
    /**
     * Checks and retrieves the customer’s entitlement to an asset.
     *
     *
     * @param id Int Item ID
     * @param entryId String Optional parameter specify the external video id
     * @param callback InPlayerCallback<InPlayerItemAccess, InPlayerException>
     */
    fun checkAccessForAsset(
        id: Int,
        entryId: String?,
        callback: InPlayerCallback<InPlayerItemAccess, InPlayerException>
    ) {
        assetService.getItemAccessUseCase.execute(GetItemAccessUseCase.Params(id, entryId))
            .subscribeOn(appSchedulers.subscribeOn)
            .observeOn(appSchedulers.observeOn)
            .subscribe({
                callback.done(InPlayerItemAccess(it), null)
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
    fun checkAccessForAsset(
        id: Int,
        callback: InPlayerCallback<InPlayerItemAccess, InPlayerException>
    ) {
        checkAccessForAsset(id, null, callback)
    }
}