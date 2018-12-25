package com.s.inplayer

import android.app.Application
import com.s.data.local.UserLocalAuthenticatorImpl
import com.s.data.model.mapper.MapInPlayerUser
import com.s.data.remote.UserRemoteAuthenticatiorImpl
import com.s.data.remote.api.InPlayerRemoteProvider
import com.s.data.repository.InPlayerAuthenticatorImpl
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.data.repository.gateway.UserRemoteAuthenticator
import com.s.domain.gateway.InPlayerAuthenticator
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.autehntication.AuthenticateUserUseCase
import com.s.domain.usecase.autehntication.CreateAccountUseCase
import com.s.domain.usecase.autehntication.IsUserAuthenticatedUseCase
import com.s.inplayer.api.Account
import com.s.inplayer.util.AppSchedulers
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

/**
 * Created by victor on 12/25/18
 */
class InPlayerSDKApplicaton : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin(this, listOf(dataModule))
    }
    
    
    // Dependency Injection for The Data Module
    val dataModule = module {
        
        factory { MapInPlayerUser() }
        
        single { AppSchedulers() as MySchedulers }
        
        factory { InPlayerRemoteProvider(BuildConfig.BASE_URL_STAGING, true) }
        
        factory { UserLocalAuthenticatorImpl(androidContext()) as UserLocalAuthenticator }
        
        factory { UserRemoteAuthenticatiorImpl(get()) as UserRemoteAuthenticator }
        
        factory { InPlayerAuthenticatorImpl(get(), get(), get()) as InPlayerAuthenticator }
        
        factory { AuthenticateUserUseCase(get(), get()) }
        
        factory { CreateAccountUseCase(get(), get()) }
        
        factory { IsUserAuthenticatedUseCase(get()) }
        
        factory { Account(get(), get(), get(), get()) }
        
    }
    
    
}