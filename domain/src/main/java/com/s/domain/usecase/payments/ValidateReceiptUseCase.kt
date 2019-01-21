package com.s.domain.usecase.payments

import com.s.domain.gateway.InPlayerPaymentRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 1/21/19
 */
class ValidateReceiptUseCase(mySchedulers: MySchedulers, private val inPlayerPaymentRepository: InPlayerPaymentRepository)
    : SingleUseCase<String, ValidateReceiptUseCase.Params>(mySchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        params?.let {
            return inPlayerPaymentRepository.validateReceipt(receipt = it.receipt, itemId = it.itemId, accessFeeId = it.accessFeeId)
        }
        
        throw IllegalStateException("Params can't be null for ValidateReceiptUseCase")
        
    }
    
    data class Params(val receipt: String, val itemId: Int, val accessFeeId: Int)
}