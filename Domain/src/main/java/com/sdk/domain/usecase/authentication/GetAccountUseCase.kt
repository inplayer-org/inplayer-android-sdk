package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository


class GetAccountUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAuthenticatorRepository.authenticatedUserAccount()
    
}