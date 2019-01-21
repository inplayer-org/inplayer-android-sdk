package com.s.domain.gateway

import io.reactivex.Single

/**
 * Created by victor on 1/21/19
 */
interface InPlayerPaymentRepository {
    
    fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int): Single<String>
}