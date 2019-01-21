package com.s.inplayer.api

import android.annotation.SuppressLint
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.payments.ValidateReceiptUseCase
import com.s.inplayer.callback.InPlayerCallback
import com.s.inplayer.mapper.ThrowableToInPlayerExceptionMapper
import com.s.inplayer.model.error.InPlayerException

/**
 * Created by victor on 1/21/19
 */
@SuppressLint("CheckResult")
class Payment(private val appSchedulers: MySchedulers,
              private val validateReceiptUseCase: ValidateReceiptUseCase) {
    
    
    fun validate(receipt: String, itemId: Int, accessFeeId: Int, callback: InPlayerCallback<String, InPlayerException>) {
        validateReceiptUseCase.execute(ValidateReceiptUseCase.Params(receipt = receipt, accessFeeId = accessFeeId, itemId = itemId))
                .observeOn(appSchedulers.observeOn)
                .subscribeOn(appSchedulers.subscribeOn)
                .subscribe({
                    callback.done(it, null)
                }, {
                    callback.done(null, ThrowableToInPlayerExceptionMapper.mapThrowableToException(it))
                })
    }
}