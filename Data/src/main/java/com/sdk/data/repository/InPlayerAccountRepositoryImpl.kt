package com.sdk.data.repository

import android.util.Base64
import com.sdk.data.model.account.InPlayerAuthorizationModel
import com.sdk.data.model.mapper.MapAuthorizationModel
import com.sdk.data.model.mapper.UserModelMapper
import com.sdk.data.model.mapper.account.MapRegisterFields
import com.sdk.data.repository.gateway.AccountRemote
import com.sdk.data.repository.gateway.UserLocalAuthenticator
import com.sdk.domain.entity.account.AuthorizationHolder
import com.sdk.domain.entity.account.CredentialsEntity
import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.entity.account.RegisterFieldsEntity
import com.sdk.domain.gateway.InPlayerAccountRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.json.JSONObject


class InPlayerAccountRepositoryImpl constructor(
    private val accountRemote: AccountRemote,
    private val userLocalAuthenticator: UserLocalAuthenticator,
    private val userModelMapper: UserModelMapper,
    private val mapRegisterFields: MapRegisterFields,
    private val mapAuthorizationModel: MapAuthorizationModel
) : InPlayerAccountRepository {
    
    /**
     *  Creating Users and handling Authorization
     * */
    override fun createAccount(
        fullName: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        type: String,
        merchantUUID: String,
        referrer: String?,
        metadata: HashMap<String, String>?,
        brandingId: String?
    ): Single<AuthorizationHolder> {
        return accountRemote
            .createAccount(
                fullName,
                email,
                password,
                passwordConfirmation,
                type,
                merchantUUID,
                referrer,
                metadata,
                brandingId
            )
            .doOnSuccess {
                updateLocalTokens(it)
            }
            .map { mapAuthorizationModel.mapFromModel(it) }
    }
    
    
    override fun authenticate(
        username: String,
        password: String,
        grantType: String,
        clientId: String
    ): Single<AuthorizationHolder> {
        return accountRemote
            .authenticateUser(username, password, grantType, clientId)
            .doOnSuccess {
                updateLocalTokens(it)
            }
            .map { mapAuthorizationModel.mapFromModel(it) }
    }
    
    override fun exportUserData(password: String, brandingId: String?): Single<String> {
        return accountRemote
            .exportUserData(password, brandingId)
            .map {
                it.message
            }
    }
    
    override fun logout(): Completable {
        return accountRemote
            .logOut()
            .doOnSuccess {
                cleanLocalPrefs()
            }.toCompletable()
    }
    
    override fun isUserAuthenticated() = userLocalAuthenticator.isUserAutehnticated()
    
    override fun tokenExpirationTime() = userLocalAuthenticator.getExpiresAt()
    
    override fun authenticatedUserAccount(): InPlayerDomainUser? {
        userLocalAuthenticator.getAccount()?.let {
            return userModelMapper.mapFromModel(it)
        }
        return null
    }
    
    override fun refreshToken(
        refreshToken: String,
        grantType: String,
        clientId: String
    ): Single<AuthorizationHolder> {
        return accountRemote.refreshToken(refreshToken, grantType, clientId)
            .doOnSuccess {
                updateLocalTokens(it)
            }.map { mapAuthorizationModel.mapFromModel(it) }
        
    }
    
    override fun clientCredentialsAuthentication(
        clientSecret: String,
        grantType: String,
        clientId: String
    ): Single<AuthorizationHolder> {
        return accountRemote.authenticateWithClientSecret(
            clientSecret = clientSecret,
            grantType = grantType,
            clientId = clientId
        )
            .doOnSuccess {
                updateLocalTokens(it)
            }.map { mapAuthorizationModel.mapFromModel(it) }
    }
    
    private fun updateLocalTokens(it: InPlayerAuthorizationModel) {
        userLocalAuthenticator.saveRefreshToken(it.refreshToken, it.expires)
        
        it.account.let {
            userLocalAuthenticator.saveCurrentUser(it)
        }
        
        userLocalAuthenticator.saveAuthenticationToken(it.accessToken)
    }
    
    private fun cleanLocalPrefs() {
        userLocalAuthenticator.deleteRefreshToken()
        userLocalAuthenticator.deleteAuthentiationToken()
        userLocalAuthenticator.deleteCurrentUser()
    }
    
    
    override fun getUserCredentials(): CredentialsEntity {
        return CredentialsEntity(
            accessToken = userLocalAuthenticator.getAuthenticationToken(),
            refreshToken = userLocalAuthenticator.getRefreshToken()
        )
    }
    
    override fun getSocialUrls(
        clientId: String,
        redirectUri: String
    ): Single<ArrayList<HashMap<String, String>>> {
        
        val jsonObject = JSONObject()
        jsonObject.put("client_id", clientId)
        jsonObject.put("redirect", "viktor.inplayer://")
        
        val base64 = Base64.encodeToString(jsonObject.toString().toByteArray(), Base64.DEFAULT)
        
        return accountRemote.getSocialUrls(base64)
    }
    
    /**
     * END Creating Users and handling Authorization
     * */
    
    
    /**
     * Account data
     * */
    
    override fun getUser(): Single<InPlayerDomainUser> {
        return accountRemote
            .accountDetails()
            .map { userModelMapper.mapFromModel(it) }
    }
    
    override fun updateUser(
        fullName: String,
        metadata: HashMap<String, String>?
    ): Single<InPlayerDomainUser> {
        return accountRemote.updateAccount(fullName, metadata)
            .map { userModelMapper.mapFromModel(it) }
    }
    
    override fun eraseUser(password: String, brandingId: String?): Single<String> {
        return accountRemote
            .eraseUser(password, brandingId)
            .doOnSuccess {
                cleanLocalPrefs()
            }.map {
                it.message
            }
    }
    
    override fun changePassword(
        newPassword: String,
        newPasswordConfirmation: String,
        oldPassword: String,
        brandingId: String?
    ): Single<String> {
        return accountRemote
            .changePassword(newPassword, newPasswordConfirmation, oldPassword, brandingId)
            .map { it.explain }
    }
    
    override fun setNewPassword(
        token: String,
        password: String,
        passwordConfirmation: String,
        brandingId: String?
    ): Completable {
        return accountRemote.setNewPassword(token, password, passwordConfirmation, brandingId)
    }
    
    override fun requestForgotPassword(merchantUUID: String, email: String, brandingId: String?): Single<String> {
        return accountRemote.forgotPassword(merchantUUID, email, brandingId)
            .map { it.explain }
    }
    
    
    override fun getRegisterFields(merchantUUID: String): Single<List<RegisterFieldsEntity>> {
        return accountRemote.getRegisterFields(merchantUUID).map {
            it.map { listItem ->
                mapRegisterFields.mapFromModel(listItem)
            }
        }
    }
    
    override fun authenticateWithSocialUrl(
        token: String,
        refreshToken: String,
        expires: Long
    ): Single<InPlayerDomainUser> {
        userLocalAuthenticator.saveAuthenticationToken(token)
        userLocalAuthenticator.saveRefreshToken(refreshToken, expires)
        return getUser()
    }
    
    override fun validatePinCode(pinCode: String): Completable {
        return accountRemote.validatePinCode(pinCode)
    }
    
    override fun sendPinCode(brandingId: String?): Completable {
        return accountRemote.sendPinCode(brandingId)
    }
    
    /**
     * END Account data
     * */
    
}