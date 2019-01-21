package com.s.data.repository.gateway

import io.reactivex.Single

/**
 * Created by victor on 1/21/19
 */
interface PaymentsRemote {
    
    fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int): Single<String>
}