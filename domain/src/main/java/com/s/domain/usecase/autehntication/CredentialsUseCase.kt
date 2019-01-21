package com.s.domain.usecase.autehntication

import com.s.domain.gateway.InPlayerAccountRepository

/**
 * Created by victor on 1/21/19
 */
class CredentialsUseCase constructor(
        private val inPlayerAccountRepository: InPlayerAccountRepository) {
    
    fun execute() = inPlayerAccountRepository.getUserCredentials()
    
}