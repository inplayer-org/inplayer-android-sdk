package com.sdk.data.repository.gateway

import io.reactivex.Single


interface PaymentsRemote {
    
    fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int): Single<String>
}