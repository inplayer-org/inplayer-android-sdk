package com.sdk.data.remote

import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.PaymentsRemote

class PaymentsRemoteImpl constructor(
    private val inPlayerRemoteServiceAPI: InPlayerRemoteServiceAPI
) : PaymentsRemote {
    
    
    override fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int) =
        inPlayerRemoteServiceAPI.validateAndroidReceipt(
            receipt = receipt,
            item_id = itemId,
            access_fee_id = accessFeeId
        ).map {
            it.message
        }
    
    override fun validateByProductName(receipt: String, productName: String) =
        inPlayerRemoteServiceAPI.validateByProductName(receipt, productName).map { it.message }
    
    override fun getCustomerAccessList(status: String, page: Int, limit: Int, type: String?) =
        inPlayerRemoteServiceAPI.getCustomerAccessList(status, page, limit, type)
    
}