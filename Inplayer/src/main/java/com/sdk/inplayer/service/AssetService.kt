package com.sdk.inplayer.service

import com.sdk.domain.usecase.assets.GetAccessFeesUseCase
import com.sdk.domain.usecase.assets.GetItemAccessUseCase
import com.sdk.domain.usecase.assets.GetItemDetailsUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class AssetService : KoinComponent {
    
    val getItemAccessUseCase: GetItemAccessUseCase by inject()
    
    val getAccessFeesUseCase: GetAccessFeesUseCase by inject()
    
    val getItemDetailsUseCase: GetItemDetailsUseCase by inject()
}