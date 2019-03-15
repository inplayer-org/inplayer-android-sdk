package com.sdk.inplayer.api

import android.annotation.SuppressLint
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.payments.GetItemAccessListUseCase
import com.sdk.domain.usecase.payments.ValidateReceiptUseCase
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.sdk.inplayer.mapper.payment.CustomerAccessItemMapper
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.model.payment.InPlayerCustomerAccessItem
import com.sdk.inplayer.service.PaymentService


@SuppressLint("CheckResult")
class Payment internal constructor(private val appSchedulers: InPlayerSchedulers,
                                   private val customerAccessItemMapper: CustomerAccessItemMapper,
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
    
    
    /**
     *
     * Get all the Customer/Merchant subscription records
     *
     * @param status The status of the purchase - (all, active, inactive)
     * @param page The current page
     * @param limit The number of items per page
     *
     *
     * */
    fun getPurchaseHistory(status: String, page: Int, limit: Int, type: String?, callback: InPlayerCallback<List<InPlayerCustomerAccessItem>, InPlayerException>) {
        paymentService.getItemAccessListUseCase
                .execute(GetItemAccessListUseCase.Params(status = status, page = page, limit = limit, type = type))
                .observeOn(appSchedulers.observeOn)
                .subscribeOn(appSchedulers.subscribeOn)
                .subscribe({ list ->
                    callback.done(list.map { customerAccessItemMapper.mapFromDomain(it) }, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}