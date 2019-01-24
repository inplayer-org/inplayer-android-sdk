package com.sdk.data.model.mapper

import com.sdk.data.model.account.InPlayerAuthorizationModel
import com.sdk.domain.entity.account.AuthorizationHolder


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