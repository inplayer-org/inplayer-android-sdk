package com.sdk.domain.usecase.account

import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import java.net.URLDecoder


/**
 * ValidateSocialLoginUseCase
 * Created on 2019-05-02
 */
class ValidateSocialLoginUseCase(
    val appSchedulers: InPlayerSchedulers,
    val inPlayerAuthenticatorRepository: InPlayerAccountRepository
) : SingleUseCase<InPlayerDomainUser, ValidateSocialLoginUseCase.Params>(appSchedulers) {
    
    
    override fun buildUseCaseObservable(params: Params?): Single<InPlayerDomainUser> {
        
        
        params?.let {
            
            val getParamsFromUri = it.toString().split("://#")[1]
            
            val components = splitQuery(getParamsFromUri)
            
            val token = components["token"]
            val refreshToken = components["refresh_token"]
            
            return inPlayerAuthenticatorRepository.authenticateWithSocialUrl(
                token!!,
                refreshToken!!
            )
        }
        
        throw IllegalStateException("Params can't be null for ValidateSocialLoginUseCase")
        
    }
    
    fun splitQuery(url: String): Map<String, String> {
        val query_pairs = LinkedHashMap<String, String>()
        
        val pairs = url.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (pair in pairs) {
            val idx = pair.indexOf("=")
            query_pairs[URLDecoder.decode(pair.substring(0, idx), "UTF-8")] =
                URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
        }
        return query_pairs
    }
    
    
    data class Params(val redirectUriString: String)
    
}