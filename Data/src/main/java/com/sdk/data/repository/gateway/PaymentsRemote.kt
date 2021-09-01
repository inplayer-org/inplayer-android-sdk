package com.sdk.data.repository.gateway

import com.sdk.data.model.ResponseListModel
import com.sdk.data.model.payment.CustomerAccessItemModel
import io.reactivex.Single


interface PaymentsRemote {
    
    fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int, brandingId: String?): Single<String>
    
    fun validateByProductName(receipt: String, productName: String, brandingId: String?): Single<String>
    
    fun getCustomerAccessList(status: String, page: Int, limit: Int, type: String?): Single<ResponseListModel<CustomerAccessItemModel>>
    
}