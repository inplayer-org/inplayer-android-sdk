package com.sdk.data.repository

import com.sdk.data.model.mapper.MapCustomerAccessItem
import com.sdk.data.repository.gateway.PaymentsRemote
import com.sdk.domain.entity.payment.CustomerAccessItemEntity
import com.sdk.domain.gateway.InPlayerPaymentRepository
import io.reactivex.Single


class InPlayerPaymentsRepositoryImpl constructor(
    private val paymentsRemote: PaymentsRemote,
    private val mapCustomerAccessItem: MapCustomerAccessItem
) : InPlayerPaymentRepository {
    
    override fun validateReceiptByProductName(
        receipt: String,
        productName: String,
        brandingId: String?
    ) = paymentsRemote.validateByProductName(receipt, productName, brandingId)
    
    override fun validateReceipt(receipt: String, itemId: Int, accessFeeId: Int, brandingId: String?) =
        paymentsRemote.validateReceipt(receipt, itemId, accessFeeId, brandingId)
    
    override fun getCustomerAccessList(
        status: String,
        page: Int,
        limit: Int,
        type: String?
    ): Single<List<CustomerAccessItemEntity>> {
        return paymentsRemote.getCustomerAccessList(status, page, limit, type)
            .map {
                it.collection.map { customerAccessItem ->
                    mapCustomerAccessItem.mapFromModel(customerAccessItem)
                }
            }
    }
    
}