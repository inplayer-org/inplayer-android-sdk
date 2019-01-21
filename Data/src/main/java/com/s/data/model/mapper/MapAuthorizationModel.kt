package com.s.data.model.mapper

import com.s.data.model.account.InPlayerAuthorizationModel
import com.s.domain.entity.account.AuthorizationHolder

/**
 * Created by victor on 1/21/19
 */
class MapAuthorizationModel  constructor(private val mapInPlayerUser: MapInPlayerUser): ModelMapper<InPlayerAuthorizationModel,AuthorizationHolder> {
    
    override fun mapFromModel(model: InPlayerAuthorizationModel): AuthorizationHolder {
        return AuthorizationHolder(
                accessToken = model.accessToken,
                refreshToken = model.refreshToken,
                account = mapInPlayerUser.mapFromModel(model.account))
    }
    
    override fun mapToModel(entity: AuthorizationHolder): InPlayerAuthorizationModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
}