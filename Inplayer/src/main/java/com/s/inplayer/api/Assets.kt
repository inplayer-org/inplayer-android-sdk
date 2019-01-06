package com.s.inplayer.api

import com.s.domain.entity.asset.AccessFee
import com.s.domain.entity.asset.ItemAccess
import com.s.domain.entity.asset.ItemDetails
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.assets.GetAccessFeesUseCase
import com.s.domain.usecase.assets.GetItemAccessUseCase
import com.s.domain.usecase.assets.GetItemDetailsUseCase
import com.s.inplayer.InPlayerSDKConfiguration
import com.s.inplayer.callback.InPlayerCallback
import com.s.inplayer.callback.error.InPlayerException
import com.s.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.s.inplayer.util.AppSchedulers

/**
 * Created by victor on 1/3/19
 */
class Assets constructor(private val appSchedulers: MySchedulers,
                         private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
                         private val getItemAccessUseCase: GetItemAccessUseCase,
                         private val getAccessFeesUseCase: GetAccessFeesUseCase,
                         private val getItemDetailsUseCase: GetItemDetailsUseCase) {
    
    
    fun getItemDetails(id: Int, callback: InPlayerCallback<ItemDetails, InPlayerException>) {
        getItemDetailsUseCase.execute(GetItemDetailsUseCase.Params(id, inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    //callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun getAccessFees(id: Int, callback: InPlayerCallback<AccessFee, InPlayerException>) {
        getAccessFeesUseCase.execute(GetAccessFeesUseCase.Params(id))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    //callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun getItemAccess(id: Int, callback: InPlayerCallback<ItemAccess, InPlayerException>) {
        getItemAccessUseCase.execute(GetItemAccessUseCase.Params(id))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    //callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}