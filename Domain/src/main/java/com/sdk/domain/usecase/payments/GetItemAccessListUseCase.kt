package com.sdk.domain.usecase.payments

import com.sdk.domain.entity.payment.CustomerAccessItemEntity
import com.sdk.domain.gateway.InPlayerPaymentRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single


/**
 * Created by victor on 3/13/19
 */
class GetItemAccessListUseCase constructor(private val inPlayerSchedulers: InPlayerSchedulers,
                                           private val inPlayerPaymentRepository: InPlayerPaymentRepository)
    : SingleUseCase<List<CustomerAccessItemEntity>, GetItemAccessListUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<List<CustomerAccessItemEntity>> {
        
        params?.let {
            return inPlayerPaymentRepository.getCustomerAccessList(status = params.status, page = params.page, limit = params.limit, type = params.type)
        }
        
        throw IllegalStateException("Params can't be null for GetItemAccessListUseCase")
    }
    
    
    data class Params(val status: String, val page: Int, val limit: Int, val type: String?)
    
}