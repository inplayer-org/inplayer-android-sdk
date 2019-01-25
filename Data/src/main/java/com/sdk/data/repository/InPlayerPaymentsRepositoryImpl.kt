package com.sdk.data.repository

import com.sdk.data.repository.gateway.PaymentsRemote
import com.sdk.domain.gateway.InPlayerPaymentRepository


class InPlayerPaymentsRepositoryImpl constructor(private val paymentsRemote: PaymentsRemote) : InPlayerPaymentRepository {
    
    override fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int)
            = paymentsRemote.validateReceipt(receipt, itemId, accessFeeId)
}