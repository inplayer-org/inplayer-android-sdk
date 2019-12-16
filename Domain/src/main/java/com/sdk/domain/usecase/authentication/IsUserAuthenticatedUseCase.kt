package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository

class IsUserAuthenticatedUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute(): Boolean {
        val isAuthenticated = inPlayerAuthenticatorRepository.isUserAuthenticated()
        val isExpired =
            System.currentTimeMillis() > inPlayerAuthenticatorRepository.tokenExpirationTime()
        return isAuthenticated && !isExpired
    }
}
