package com.sdk.domain.gateway

import com.sdk.domain.entity.payment.CustomerAccessItemEntity
import io.reactivex.Single


interface InPlayerPaymentRepository {
    
    fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int, brandingId: String? = null): Single<String>
    
    fun validateReceiptByProductName(receipt: String, productName: String, brandingId: String? = null): Single<String>
    
    fun getCustomerAccessList(
        status: String,
        page: Int,
        limit: Int,
        type: String?
    ): Single<List<CustomerAccessItemEntity>>
}