package com.sdk.domain.gateway

import io.reactivex.Single


interface InPlayerPaymentRepository {
    
    fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int): Single<String>
}