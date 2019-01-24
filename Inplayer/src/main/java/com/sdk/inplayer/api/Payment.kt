package com.sdk.inplayer.api

import android.annotation.SuppressLint
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.payments.ValidateReceiptUseCase
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.service.PaymentService


@SuppressLint("CheckResult")
class Payment internal constructor(private val appSchedulers: InPlayerSchedulers,
                                   private val paymentService: PaymentService) {
    
    
    /**
     * Validates an In App purchase from Google Play store services
     *
     *
     * @param receipt String The Purchase object from Google Play response after a successful purchase
     * @param productIdentifier String Product identifier in a format itemId_accessFeeId
     * @param callback InPlayerCallback<String, InPlayerException>
     */
    fun validate(receipt: String, productIdentifier: String, callback: InPlayerCallback<String, InPlayerException>) {
        paymentService.validateReceiptUseCase.execute(ValidateReceiptUseCase.Params(receipt = receipt, productIdentifier = productIdentifier))
                .observeOn(appSchedulers.observeOn)
                .subscribeOn(appSchedulers.subscribeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}