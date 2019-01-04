package com.s.domain.usecase.autehntication

import com.s.domain.gateway.InPlayerAccountRepository

/**
 * Created by victor on 12/20/18
 */
class IsUserAuthenticatedUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAuthenticatorRepository.isUserAuthenticated()
    
}