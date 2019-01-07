package com.s.inplayer.api

import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.assets.GetAccessFeesUseCase
import com.s.domain.usecase.assets.GetItemAccessUseCase
import com.s.domain.usecase.assets.GetItemDetailsUseCase
import com.s.inplayer.InPlayerSDKConfiguration
import com.s.inplayer.callback.InPlayerCallback
import com.s.inplayer.callback.error.InPlayerException
import com.s.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.s.inplayer.mapper.assets.MapAccessFee
import com.s.inplayer.mapper.assets.MapItemAccess
import com.s.inplayer.mapper.assets.MapItemDetails
import com.s.inplayer.model.assets.AccessFee
import com.s.inplayer.model.assets.ItemAccess
import com.s.inplayer.model.assets.ItemDetails

/**
 * Created by victor on 1/3/19
 */
class Assets constructor(private val appSchedulers: MySchedulers,
                         private val inPlayerSDKConfiguration: InPlayerSDKConfiguration,
                         private val getItemAccessUseCase: GetItemAccessUseCase,
                         private val getAccessFeesUseCase: GetAccessFeesUseCase,
                         private val getItemDetailsUseCase: GetItemDetailsUseCase,
                         private val mapItemDetails: MapItemDetails,
                         private val mapAccessFee: MapAccessFee,
                         private val mapItemAccess: MapItemAccess) {
    
    
    fun getItemDetails(id: Int, callback: InPlayerCallback<ItemDetails, InPlayerException>) {
        getItemDetailsUseCase.execute(GetItemDetailsUseCase.Params(id, inPlayerSDKConfiguration.merchantUUID))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(mapItemDetails.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun getAccessFees(id: Int, callback: InPlayerCallback<List<AccessFee>, InPlayerException>) {
        getAccessFeesUseCase.execute(GetAccessFeesUseCase.Params(id))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({ list ->
                    callback.done(list.map { mapAccessFee.mapFromDomain(it) }, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
    
    fun getItemAccess(id: Int, callback: InPlayerCallback<ItemAccess, InPlayerException>) {
        getItemAccessUseCase.execute(GetItemAccessUseCase.Params(id))
                .subscribeOn(appSchedulers.subscribeOn)
                .observeOn(appSchedulers.observeOn)
                .subscribe({
                    callback.done(mapItemAccess.mapFromDomain(it), null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}