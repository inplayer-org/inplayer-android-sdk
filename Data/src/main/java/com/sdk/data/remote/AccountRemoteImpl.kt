package com.sdk.data.remote

import com.sdk.data.model.account.InPlayerAccount
import com.sdk.data.model.account.InPlayerAuthorizationModel
import com.sdk.data.model.account.InPlayerRegisterFieldsModel
import com.sdk.data.remote.api.InPlayerRemotePublicServiceAPI
import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.repository.gateway.AccountRemote
import io.reactivex.Single


class AccountRemoteImpl constructor(
    private val inPlayerRemoteProvider: InPlayerRemoteServiceAPI,
    private val inPlayerRemotePublicServiceAPI: InPlayerRemotePublicServiceAPI
) : AccountRemote {
    
    
    /**
     * API's that are public and don't require Auth Token in their Header, should be calling the
     * InPlayerRemotePublicProvider
     * */
    override fun authenticateUser(
        username: String,
        password: String,
        grantType: String,
        clientId: String
    ) = inPlayerRemotePublicServiceAPI.authenticate(
        username = username,
        password = password,
        grantType = grantType.toLowerCase(),
        clientId = clientId
    )
    
    override fun refreshToken(refreshToken: String, grantType: String, clientId: String) =
        inPlayerRemotePublicServiceAPI.authenticate(
            refreshToken = refreshToken,
            grantType = grantType,
            clientId = clientId
        )
    
    override fun authenticateWithClientSecret(
        clientSecret: String,
        grantType: String,
        clientId: String
    ) =
        inPlayerRemotePublicServiceAPI.authenticate(
            clientSecret = clientSecret,
            grantType = grantType,
            clientId = clientId
        )
    
    override fun createAccount(
        fullName: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        type: String,
        merchantUUID: String,
        referrer: String?,
        metadata: HashMap<String, String>?
    ): Single<InPlayerAuthorizationModel> {
        var updatedMetadataMap = hashMapOf<String, String>()
        
        metadata?.forEach {
            updatedMetadataMap["metadata[${it.key}]"] = it.value
        }
        
        return inPlayerRemotePublicServiceAPI.createAccount(
            fullName,
            email,
            password,
            passwordConfirmation,
            type.toLowerCase(),
            merchantUUID,
            referrer,
            updatedMetadataMap
        )
    }
    
    
    override fun setNewPassword(token: String, password: String, passwordConfirmation: String) =
        inPlayerRemotePublicServiceAPI.setNewPassword(token, password, passwordConfirmation)
    
    override fun forgotPassword(merchantUUID: String, email: String) =
        inPlayerRemotePublicServiceAPI.forgotPassword(merchantUUID, email)
    
    override fun getSocialUrls(state: String): Single<ArrayList<HashMap<String, String>>> {
        return inPlayerRemoteProvider.getSocialUrls(state).map {
            it.socialUrls
        }
    }
    
    /**
     * END
     * */
    
    
    /**
     * API's that are NOT public and require Auth Token in their Header, should be calling the
     * InPlayerRemoteProvider
     * */
    
    override fun accountDetails() = inPlayerRemoteProvider.getAccount()
    
    override fun getRegisterFields(merchantUUID: String): Single<List<InPlayerRegisterFieldsModel>> {
        return inPlayerRemoteProvider.exportRegisterFields(merchantUUID).map {
            it.collection
        }
    }
    
    override fun exportUserData(password: String) =
        inPlayerRemoteProvider.exportAccountData(password)
    
    
    override fun updateAccount(
        fullName: String,
        metadata: HashMap<String, String>?
    ): Single<InPlayerAccount> {
        
        var updatedMetadataMap = hashMapOf<String, String>()
        
        metadata?.forEach {
            updatedMetadataMap["metadata[${it.key}]"] = it.value
        }
        
        return inPlayerRemoteProvider.updateAccount(fullName, updatedMetadataMap)
    }
    
    override fun changePassword(
        newPassword: String,
        newPasswordConfirmation: String,
        oldPassword: String
    ) = inPlayerRemoteProvider.changePassword(newPassword, newPasswordConfirmation, oldPassword)
    
    override fun logOut() = inPlayerRemoteProvider.logout()
    
    override fun eraseUser(password: String) = inPlayerRemoteProvider.eraseAccount(password)
    
    /**
     * END
     * */
    
    
}
