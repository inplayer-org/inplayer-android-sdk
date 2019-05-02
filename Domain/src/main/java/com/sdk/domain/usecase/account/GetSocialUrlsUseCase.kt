package com.sdk.domain.usecase.account

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by Victor on 2019-04-24
 */
class GetSocialUrlsUseCase(
    val appSchedulers: InPlayerSchedulers,
    val inPlayerAuthenticatorRepository: InPlayerAccountRepository
) : SingleUseCase<ArrayList<HashMap<String, String>>, GetSocialUrlsUseCase.Params>(appSchedulers) {
    
    override fun buildUseCaseObservable(params: Params?): Single<ArrayList<HashMap<String, String>>> {
        
        params?.let {
            return inPlayerAuthenticatorRepository.getSocialUrls(
                params.clientId,
                params.redirectUri
            )
        }
        
        throw IllegalStateException("Params can't be null for GetSocialUrlsUseCase")
        
    }
    
    
    data class Params(val clientId: String, val redirectUri: String)
    
}