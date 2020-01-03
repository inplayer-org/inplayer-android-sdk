package com.sdk.domain.usecase.payments

import com.sdk.domain.gateway.InPlayerPaymentRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class ValidateReceiptUseCase(
    inPlayerSchedulers: InPlayerSchedulers,
    private val inPlayerPaymentRepository: InPlayerPaymentRepository
) : SingleUseCase<String, ValidateReceiptUseCase.Params>(inPlayerSchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        params?.let {
            if (params is Params.ProductId) {
                return validateByProductId(params)
            } else if (params is Params.ProductName) {
                return validateByProductName(params)
            }
        }
        
        throw IllegalStateException("Params can't be null for ValidateReceiptUseCase")
    }
    
    private fun validateByProductId(
        params: Params.ProductId
    ): Single<String> {
        try {
            val values = params.productIdentifier.split("_")
            val itemId = values[0].toInt()
            val accessFeeId = values[1].toInt()
            
            return inPlayerPaymentRepository.validateReceipt(
                receipt = params.receipt,
                itemId = itemId,
                accessFeeId = accessFeeId
            )
            
        } catch (e: Exception) {
            throw IllegalStateException("Product Identifier must be of format itemId_accessFeeId")
        }
    }
    
    private fun validateByProductName(
        params: Params.ProductName
    ) = inPlayerPaymentRepository.validateReceiptByProductName(
        receipt = params.receipt,
        productName = params.productName
    )
    
    // data class Params(val receipt: String, val productIdentifier:String)
    sealed class Params(val receipt: String) {
        data class ProductId(val _receipt: String, val productIdentifier: String) : Params(_receipt)
        data class ProductName(val _receipt: String, val productName: String) : Params(_receipt)
    }
}