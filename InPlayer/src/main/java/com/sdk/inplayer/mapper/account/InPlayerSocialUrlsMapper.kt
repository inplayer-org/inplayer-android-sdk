package com.sdk.inplayer.mapper.account

import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.model.account.InPlayerSocialUrls

/**
 * InPlayerSocialUrlsMapper
 * Created on 2019-05-02
 */
class InPlayerSocialUrlsMapper : DomainMapper<HashMap<String, String>, InPlayerSocialUrls> {
    
    override fun mapFromDomain(domainEntity: HashMap<String, String>): InPlayerSocialUrls {
        
        var socialUrl = InPlayerSocialUrls("", "")
        
        for ((k, v) in domainEntity) {
            socialUrl = InPlayerSocialUrls(k, v)
            break
        }
        
        return socialUrl
    }
    
    override fun mapToDomain(viewModel: InPlayerSocialUrls): HashMap<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}