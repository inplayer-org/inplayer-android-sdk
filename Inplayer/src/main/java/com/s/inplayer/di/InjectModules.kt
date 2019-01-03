package com.s.inplayer.di

import android.content.Context
import com.s.data.local.UserLocalAuthenticatorImpl
import com.s.data.model.mapper.MapInPlayerUser
import com.s.data.remote.UserRemoteAuthenticatiorImpl
import com.s.data.remote.api.InPlayerRemoteProvider
import com.s.data.remote.interceptor.RefreshAuthenticator
import com.s.data.remote.refresh_token.InPlayerRemoteRefreshServiceAPI
import com.s.data.remote.refresh_token.InPlayerRemoteRefreshTokenProvider
import com.s.data.repository.InPlayerAccountRepositoryImpl
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.data.repository.gateway.UserRemoteAuthenticator
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.autehntication.*
import com.s.inplayer.InPlayer
import com.s.inplayer.InPlayerSDKConfiguration
import com.s.inplayer.api.Account
import com.s.inplayer.util.AppSchedulers
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.setProperty

/**
 * Created by victor on 12/27/18
 */
object InjectModules : KoinComponent {
    
    fun init(configuration: InPlayer.Configuration) {
        
        val contextModule = module {
            single<Context> { (getProperty(Const.context)) }
        }
        
        val configurationModule = module {
            single { InPlayerSDKConfiguration(getProperty(Const.merchant_UUID), getProperty(Const.referrer)) }
        }
        
        // Dependency Injection for The Data Module
        val dataModule = module {
            
            factory { MapInPlayerUser() }
            
            single { AppSchedulers() as MySchedulers }
            
            single { InPlayerRemoteRefreshTokenProvider(getProperty(Const.serverUrl), true) as InPlayerRemoteRefreshServiceAPI }
    
            single { RefreshAuthenticator(get(), get()) }
            
            factory { InPlayerRemoteProvider(getProperty(Const.serverUrl), true, get()) }
            
            factory { UserLocalAuthenticatorImpl(get()) as UserLocalAuthenticator }
            
            factory { UserRemoteAuthenticatiorImpl(get()) as UserRemoteAuthenticator }
            
            factory { InPlayerAccountRepositoryImpl(get(), get(), get()) as InPlayerAccountRepository }
            
        }
        
        val mainControllerModule = module {
            factory { Account(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
        }
        
        val accountUseCaseModule = module {
            
            factory { ForgotPasswordUseCase(get(), get()) }
            
            factory { EraseUserUseCase(get(), get()) }
            
            factory { AuthenticateUserUseCase(get(), get()) }
            
            factory { CreateAccountUseCase(get(), get()) }
            
            factory { IsUserAuthenticatedUseCase(get()) }
            
            factory { LogOutUserUseCase(get(), get()) }
            
            factory { AccountDetailsUseCase(get(), get()) }
            
            factory { ChangePasswordUseCase(get(), get()) }
            
            factory { UpdateUserUseCase(get(), get()) }
            
            factory { SetNewPasswordUseCase(get(), get()) }
        }
        
        startKoin(listOf(contextModule, configurationModule, dataModule, accountUseCaseModule, mainControllerModule))
        
        setProperty(Const.context, configuration.context)
        
        setProperty(Const.serverUrl, configuration.mServerUrl)
        
        setProperty(Const.merchant_UUID, configuration.mMerchantUUID)
        
        setProperty(Const.referrer, configuration.referrer)
        
    }
    
    object Const {
        
        val context = "context"
        
        val serverUrl = "server_url"
        
        val merchant_UUID = "merchant_uuid"
        
        val referrer = "referrer"
    }
}