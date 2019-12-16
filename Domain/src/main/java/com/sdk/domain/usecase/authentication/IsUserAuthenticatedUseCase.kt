package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository
import java.util.*

class IsUserAuthenticatedUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute(): Boolean {
        val isAuthenticated = inPlayerAuthenticatorRepository.isUserAuthenticated()
        val dateNow = Date(System.currentTimeMillis())
        val dateThen = Date(inPlayerAuthenticatorRepository.tokenExpirationTime() * 1000)
        val isExpired = dateNow.after(dateThen)
        return isAuthenticated && !isExpired
    }
}
