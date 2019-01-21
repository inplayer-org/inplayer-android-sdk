package com.s.data.repository

import com.s.data.repository.gateway.PaymentsRemote
import com.s.domain.gateway.InPlayerPaymentRepository

/**
 * Created by victor on 1/21/19
 */
class InPlayerPaymentsRepositoryImpl constructor(private val paymentsRemote: PaymentsRemote) : InPlayerPaymentRepository {
    
    override fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int)
            = paymentsRemote.validateReceipt(receipt, itemId, accessFeeId)
}