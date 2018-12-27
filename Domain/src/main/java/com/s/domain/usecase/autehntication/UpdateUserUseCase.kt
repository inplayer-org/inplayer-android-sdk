package com.s.domain.usecase.autehntication

import com.s.domain.entity.InPlayerUser
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/27/18
 */
class UpdateUserUseCase constructor(private val appSchedulers: MySchedulers,
                                    private val inPlayerAccountRepository: InPlayerAccountRepository)
    : SingleUseCase<InPlayerUser, UpdateUserUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<InPlayerUser> {
        
        params?.let {
            return inPlayerAccountRepository.updateUser(it.fullName, it.metadata)
        }
        
        throw IllegalStateException("Params Can't be null for UpdateUserUseCase")
    }
    
    
    data class Params(val fullName: String, val referrer: String? = null, val metadata: HashMap<String, String>? = null)
}