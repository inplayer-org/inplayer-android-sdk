package com.s.data.remote

import com.s.data.remote.api.InPlayerRemoteServiceAPI
import com.s.data.repository.gateway.PaymentsRemote

/**
 * Created by victor on 1/21/19
 */
class PaymentsRemoteImpl constructor(private val inPlayerRemoteServiceAPI: InPlayerRemoteServiceAPI) : PaymentsRemote {
    
    override fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int) = inPlayerRemoteServiceAPI.validateAndroidReceipt(receipt = receipt, item_id = itemId, access_fee_id = accessFeeId).map {
        it.message
    }
    
}