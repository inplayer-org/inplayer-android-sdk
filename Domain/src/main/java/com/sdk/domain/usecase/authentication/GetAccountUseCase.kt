package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository

/**
 * Created by victor on 1/22/19
 */
class GetAccountUseCase constructor(private val inPlayerAuthenticatorRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAuthenticatorRepository.authenticatedUserAccount()
    
}