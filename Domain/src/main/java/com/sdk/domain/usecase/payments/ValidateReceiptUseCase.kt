package com.sdk.domain.usecase.payments

import com.sdk.domain.gateway.InPlayerPaymentRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import java.lang.Exception


class ValidateReceiptUseCase(inPlayerSchedulers: InPlayerSchedulers, private val inPlayerPaymentRepository: InPlayerPaymentRepository)
    : SingleUseCase<String, ValidateReceiptUseCase.Params>(inPlayerSchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        params?.let {
            
            try {
                val values = params.productIdentifier.split("_")
                val itemId = values[0].toInt()
                val accessFeeId = values[1].toInt()
          
                return inPlayerPaymentRepository.validateReceipt(receipt = it.receipt, itemId = itemId, accessFeeId = accessFeeId)
                
            }catch (e : Exception){
                throw IllegalStateException("Product Identifier must be of format itemId_accessFeeId")
            }
    
        }
        
        throw IllegalStateException("Params can't be null for ValidateReceiptUseCase")
        
    }
    
    data class Params(val receipt: String, val productIdentifier:String)
}