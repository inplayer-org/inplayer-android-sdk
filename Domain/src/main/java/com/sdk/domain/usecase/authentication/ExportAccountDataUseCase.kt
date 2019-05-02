package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 3/13/19
 */
class ExportAccountDataUseCase constructor(private val inPlayerSchedulers: InPlayerSchedulers,
                                           private val inPlayerAccountRepository: InPlayerAccountRepository)
    : SingleUseCase<String, ExportAccountDataUseCase.Params>(inPlayerSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<String> {
        
        params?.let {
            return inPlayerAccountRepository.exportUserData(it.password)
        }
        
        throw IllegalStateException("Params can't be null for ExportAccountDataUseCase")
    }
    
    
    data class Params(val password: String)
    
}