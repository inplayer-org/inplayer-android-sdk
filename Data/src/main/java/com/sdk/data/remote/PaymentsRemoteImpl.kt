package com.sdk.data.remote

import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.PaymentsRemote

class PaymentsRemoteImpl constructor(
    private val inPlayerRemoteServiceAPI: InPlayerRemoteServiceAPI
) : PaymentsRemote {


    override fun validateReceipt(
        receipt: String,
        itemId: Int,
        accessFeeId: Int,
        brandingId: Int?
    ) = inPlayerRemoteServiceAPI.validateAndroidReceipt(
        receipt = receipt,
        item_id = itemId,
        access_fee_id = accessFeeId,
        brandingId = brandingId
    ).map {
        it.message
    }

    override fun validateByProductName(receipt: String, productName: String, brandingId: Int?) =
        inPlayerRemoteServiceAPI.validateByProductName(receipt, productName, brandingId).map { it.message }

    override fun getCustomerAccessList(status: String, page: Int, limit: Int, type: String?) =
        inPlayerRemoteServiceAPI.getCustomerAccessList(status, page, limit, type)

}