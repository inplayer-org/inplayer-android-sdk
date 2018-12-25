package com.s.domain.usecase.autehntication

import com.s.domain.gateway.InPlayerAuthenticator

/**
 * Created by victor on 12/20/18
 */
class IsUserAuthenticatedUseCase constructor(private val inPlayerAuthenticator: InPlayerAuthenticator) {
    
    fun execute() = inPlayerAuthenticator.isUserAuthenticated()
    
}