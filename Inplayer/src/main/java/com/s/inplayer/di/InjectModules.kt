package com.s.inplayer.di

import android.content.Context
import com.s.data.local.UserLocalAuthenticatorImpl
import com.s.data.model.account.InPlayerAccount
import com.s.data.model.mapper.*
import com.s.data.remote.AccountRemoteImpl
import com.s.data.remote.AssetsRemoteImpl
import com.s.data.remote.NotificationsRemoteImpl
import com.s.data.remote.api.InPlayerRemoteProvider
import com.s.data.remote.api.InPlayerRemotePublicProvider
import com.s.data.remote.api.InPlayerRemotePublicServiceAPI
import com.s.data.remote.api.InPlayerRemoteServiceAPI
import com.s.data.remote.refresh_token.InPlayerRemoteRefreshServiceAPI
import com.s.data.remote.refresh_token.InPlayerRemoteRefreshTokenProvider
import com.s.data.remote.refresh_token.RefreshAuthenticator
import com.s.data.repository.InPlayerAWSCredentialsRepositoryImpl
import com.s.data.repository.InPlayerAccountRepositoryImpl
import com.s.data.repository.InPlayerAssetsRepositoryImpl
import com.s.data.repository.gateway.AccountRemote
import com.s.data.repository.gateway.AssetsRemote
import com.s.data.repository.gateway.NotificationsRemote
import com.s.data.repository.gateway.UserLocalAuthenticator
import com.s.domain.entity.account.InPlayerDomainUser
import com.s.domain.entity.mapper.DomainMapper
import com.s.domain.gateway.InPlayerAccountRepository
import com.s.domain.gateway.InPlayerAssetsRepository
import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.assets.GetAccessFeesUseCase
import com.s.domain.usecase.assets.GetItemAccessUseCase
import com.s.domain.usecase.assets.GetItemDetailsUseCase
import com.s.domain.usecase.autehntication.*
import com.s.inplayer.InPlayer
import com.s.inplayer.InPlayerSDKConfiguration
import com.s.inplayer.api.Account
import com.s.inplayer.api.Assets
import com.s.inplayer.mapper.InPlayerUserMapper
import com.s.inplayer.mapper.assets.*
import com.s.inplayer.model.InPlayerUser
import com.s.inplayer.util.AppSchedulers
import com.s.notification.NotificationManager
import com.s.notification.gateway.InPlayerAWSCredentialsRepository
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
            
            single { AppSchedulers() as MySchedulers }
            
            /**
             * Data Module Mapper
             * */
            single { MapDataAccessControlType() }
            
            single { MapDataAccessFee(get(), get(), get(), get()) }
            
            single { MapDataAccessType() }
            
            single { MapInPlayerUser() as ModelMapper<InPlayerAccount, InPlayerDomainUser> }
            
            single { MapDataItemAccess(get()) }
            
            single { MapDataItemDetails(get(), get()) }
            
            single { MapDataItemType() }
            
            single { MapDataSetupFee() }
            
            single { MapDataTrialPeriod() }
            
            single { MapAWSCredentials() }
            
            /**
             * END Data Module Mapper
             * */
            
            single<UserLocalAuthenticator> { UserLocalAuthenticatorImpl(get()) }
            
            /**
             * REFRESH TOKEN
             * */
            
            single { InPlayerRemoteRefreshTokenProvider(getProperty(Const.serverUrl), true) as InPlayerRemoteRefreshServiceAPI }
            
            single { RefreshAuthenticator(configuration.mMerchantUUID, get(), get()) }
            
            /**
             * END REFRESH TOKEN
             * */
            
            single { InPlayerRemotePublicProvider(getProperty(Const.serverUrl), true) as InPlayerRemotePublicServiceAPI }
            
            single { InPlayerRemoteProvider(getProperty(Const.serverUrl), true, get(), get()) as InPlayerRemoteServiceAPI }
            
            
            
            single { AccountRemoteImpl(get(), get()) as AccountRemote }
            
            single { AssetsRemoteImpl(get(), get()) as AssetsRemote }
            
            single { NotificationsRemoteImpl(true, get()) as NotificationsRemote }
            
            
            /**
             * REPOSITORY
             * */
            single { InPlayerAssetsRepositoryImpl(get(), get(), get(), get()) as InPlayerAssetsRepository }
            
            single { InPlayerAccountRepositoryImpl(get(), get(), get()) as InPlayerAccountRepository }
            
            single { InPlayerAWSCredentialsRepositoryImpl(get(), get()) as InPlayerAWSCredentialsRepository }
            
            /**
             * END REPOSITORY
             * */
            
        }
        
        val mainControllerModule = module {
            
            single { Assets(get(), get(), get(), get(), get(), get(), get(), get()) }
            
            single { Account(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
            
            single { NotificationManager(get(), get()) }
            
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
        
        val assetsUseCaseModule = module {
            
            factory { GetItemDetailsUseCase(get(), get()) }
            
            factory { GetAccessFeesUseCase(get(), get()) }
            
            factory { GetItemAccessUseCase(get(), get()) }
            
        }
        
        
        val mapperModule = module {
            
            single { InPlayerUserMapper() as DomainMapper<InPlayerDomainUser, InPlayerUser> }
            
            single { MapAccessControlType() }
            
            single { MapAccessFee(get(), get(), get(), get()) }
            
            single { MapAccessType() }
            
            single { MapInPlayerUser() }
            
            single { MapItemAccess(get()) }
            
            single { MapItemDetails(get(), get()) }
            
            single { MapItemType() }
            
            single { MapSetupFee() }
            
            single { MapTrialPeriod() }
            
            
        }
        
        startKoin(listOf(contextModule, mapperModule, configurationModule, dataModule, accountUseCaseModule,
                mainControllerModule, assetsUseCaseModule))
        
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