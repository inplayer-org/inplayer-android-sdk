package com.s.data.remote

import com.s.data.remote.api.InPlayerRemoteProvider
import com.s.data.remote.request.EraseUserRequest
import com.s.data.repository.gateway.UserRemoteAuthenticator

/**
 * Created by victor on 12/21/18
 */
class UserRemoteAuthenticatiorImpl constructor(val inPlayerRemoteProvider: InPlayerRemoteProvider) : UserRemoteAuthenticator {
    
    override fun authenticateUser(username: String, password: String, grantType: String, clientId: String) = inPlayerRemoteProvider.authenticate(username, password, grantType.toLowerCase(), clientId)
    
    override fun createAccount(fullName: String, email: String, password: String, passwordConfirmation: String, type: String, merchantUUID: String, referrer: String) = inPlayerRemoteProvider.createAccount(fullName, email, password, passwordConfirmation, type.toLowerCase(), merchantUUID, referrer)
    
    override fun logOut(token: String) = inPlayerRemoteProvider.logout(token)
    
    override fun accountDetails(token: String) = inPlayerRemoteProvider.getAccount(token)
    
    override fun eraseUser(password: String, token: String) = inPlayerRemoteProvider.eraseAccount(EraseUserRequest(password), token)
    
    override fun changePassword(newPassword: String, newPasswordConfirmation: String, oldPassword: String, token: String) = inPlayerRemoteProvider.changePassword(newPassword, newPasswordConfirmation, oldPassword, token)
    
    override fun forgotPassword(merchantUUID: String, email: String) = inPlayerRemoteProvider.forgotPassword(merchantUUID, email)
    
    override fun updateAccount(fullName: String, metadata: HashMap<String, String>?, token: String) = inPlayerRemoteProvider.updateAccount(fullName, metadata, token)
    
}
