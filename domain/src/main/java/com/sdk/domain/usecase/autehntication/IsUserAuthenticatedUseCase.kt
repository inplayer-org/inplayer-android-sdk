package com.sdk.domain.usecase.autehntication

import com.sdk.domain.gateway.InPlayerAccountRepository

/**
 * Created by victor on 12/20/18
 */
class IsUserAuthenticatedUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAuthenticatorRepository.isUserAuthenticated()
    
}