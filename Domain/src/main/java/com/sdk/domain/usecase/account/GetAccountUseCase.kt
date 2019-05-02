package com.sdk.domain.usecase.account

import com.sdk.domain.gateway.InPlayerAccountRepository


class GetAccountUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAuthenticatorRepository.authenticatedUserAccount()
    
}