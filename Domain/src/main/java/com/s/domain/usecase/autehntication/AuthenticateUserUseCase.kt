package com.s.domain.usecase.autehntication

import com.s.domain.entity.GrantType
import com.s.domain.entity.InPlayerUser
import com.s.domain.gateway.InPlayerAuthenticator
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
class AuthenticateUserUseCase constructor(schedulers: MySchedulers,
                                          private val inPlayerAuthenticator: InPlayerAuthenticator)
    : SingleUseCase<InPlayerUser, AuthenticateUserUseCase.Params>(schedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<InPlayerUser> {
        
        params?.let {
            return inPlayerAuthenticator.autehenticate(it.username, it.password, it.grantType.toString(), it.clientId)
        }
        
        throw IllegalStateException("Params Can't be null for AuthenticateUserUseCase")
    }
    
    
    data class Params(val username: String, val password: String, val grantType: GrantType, val clientId: String)
    
}