package com.sdk.domain.usecase.authentication

import com.sdk.domain.gateway.InPlayerAccountRepository


class CredentialsUseCase constructor(
        private val inPlayerAccountRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAccountRepository.getUserCredentials()
    
}