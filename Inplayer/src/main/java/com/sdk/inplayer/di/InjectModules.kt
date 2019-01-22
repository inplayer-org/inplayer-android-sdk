package com.sdk.inplayer.di

import android.content.Context
import com.sdk.data.local.UserLocalAuthenticatorImpl
import com.sdk.data.model.account.InPlayerAccount
import com.sdk.data.model.mapper.*
import com.sdk.data.remote.AccountRemoteImpl
import com.sdk.data.remote.AssetsRemoteImpl
import com.sdk.data.remote.NotificationsRemoteImpl
import com.sdk.data.remote.PaymentsRemoteImpl
import com.sdk.data.remote.api.InPlayerRemoteProvider
import com.sdk.data.remote.api.InPlayerRemotePublicProvider
import com.sdk.data.remote.api.InPlayerRemotePublicServiceAPI
import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.remote.refresh_token.InPlayerRemoteRefreshServiceAPI
import com.sdk.data.remote.refresh_token.InPlayerRemoteRefreshTokenProvider
import com.sdk.data.remote.refresh_token.RefreshAuthenticator
import com.sdk.data.repository.InPlayerAWSCredentialsRepositoryImpl
import com.sdk.data.repository.InPlayerAccountRepositoryImpl
import com.sdk.data.repository.InPlayerAssetsRepositoryImpl
import com.sdk.data.repository.InPlayerPaymentsRepositoryImpl
import com.sdk.data.repository.gateway.*
import com.sdk.domain.entity.account.InPlayerDomainUser
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.gateway.InPlayerPaymentRepository
import com.sdk.domain.schedulers.MySchedulers
import com.sdk.domain.usecase.assets.GetAccessFeesUseCase
import com.sdk.domain.usecase.assets.GetItemAccessUseCase
import com.sdk.domain.usecase.assets.GetItemDetailsUseCase
import com.sdk.domain.usecase.autehntication.*
import com.sdk.domain.usecase.payments.ValidateReceiptUseCase
import com.sdk.inplayer.configuration.InPlayer
import com.sdk.inplayer.util.InPlayerSDKConfiguration
import com.sdk.inplayer.api.Account
import com.sdk.inplayer.api.Asset
import com.sdk.inplayer.api.Notification
import com.sdk.inplayer.api.Payment
import com.sdk.inplayer.mapper.account.AuthorizationModelMapper
import com.sdk.inplayer.mapper.account.InPlayerCredentialsMapper
import com.sdk.inplayer.mapper.account.InPlayerUserMapper
import com.sdk.inplayer.mapper.assets.*
import com.sdk.inplayer.mapper.notification.AccessGrantedNotificationMapper
import com.sdk.inplayer.mapper.notification.AccessRevokedNotificationMapper
import com.sdk.inplayer.mapper.notification.NotificationMapper
import com.sdk.inplayer.util.AppSchedulers
import com.sdk.notification.AWSNotificationManager
import com.sdk.notification.gateway.InPlayerAWSCredentialsRepository
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.setProperty


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
            
            
            //Data Module Mapper
            
            factory { MapDataAccessControlType() }
            
            factory { MapDataAccessFee(get(), get(), get(), get()) }
            
            factory { MapDataAccessType() }
            
            factory { MapInPlayerUser() as ModelMapper<InPlayerAccount, InPlayerDomainUser> }
            
            factory { MapDataItemAccess(get()) }
    
            factory { MapDataItemDetails(get(), get(), get()) }
            
            factory { MapDataItemType() }
            
            factory { MapDataSetupFee() }
            
            factory { MapDataTrialPeriod() }
            
            factory { MapAWSCredentials() }
            
            factory { MapAuthorizationModel(get()) }
            
            //END Data Module Mapper
            
            factory<UserLocalAuthenticator> { UserLocalAuthenticatorImpl(get()) }
            
            //REFRESH TOKEN
            
            factory { InPlayerRemoteRefreshTokenProvider(getProperty(Const.serverUrl), configuration.isDebug) as InPlayerRemoteRefreshServiceAPI }
            
            factory { RefreshAuthenticator(configuration.mMerchantUUID, get(), get()) }
            
            //END REFRESH TOKEN
            
            single { InPlayerRemotePublicProvider(getProperty(Const.serverUrl), configuration.isDebug) as InPlayerRemotePublicServiceAPI }
            
            single { InPlayerRemoteProvider(getProperty(Const.serverUrl), configuration.isDebug, get(), get()) as InPlayerRemoteServiceAPI }
            
            
            
            factory { AccountRemoteImpl(get(), get()) as AccountRemote }
            
            factory { AssetsRemoteImpl(get(), get()) as AssetsRemote }
            
            factory { NotificationsRemoteImpl(configuration.isDebug, get()) as NotificationsRemote }
            
            factory { PaymentsRemoteImpl(get()) as PaymentsRemote }
            
            //REPOSITORY
            
            factory { InPlayerAssetsRepositoryImpl(get(), get(), get(), get()) as InPlayerAssetsRepository }
            
            factory { InPlayerAccountRepositoryImpl(get(), get(), get(),get()) as InPlayerAccountRepository }
            
            factory { InPlayerAWSCredentialsRepositoryImpl(get(), get(), get()) as InPlayerAWSCredentialsRepository }
            
            factory { InPlayerPaymentsRepositoryImpl(get()) as InPlayerPaymentRepository }
            
            //END REPOSITORY
            
        }
        
        val mainControllerModule = module {
            
            factory { Asset(get(), get(), get(), get(), get(), get(), get(), get()) }
            
            factory { Account(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(),get()) }
            
            factory { Notification(get(), get()) }
            
            factory { Payment(get(), get()) }
            
            
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
            
            factory { CredentialsUseCase(get()) }
        }
        
        val assetsUseCaseModule = module {
            
            factory { GetItemDetailsUseCase(get(), get()) }
            
            factory { GetAccessFeesUseCase(get(), get()) }
            
            factory { GetItemAccessUseCase(get(), get()) }
            
        }
        
        val paymentUseCaseModule = module {
            
            factory { ValidateReceiptUseCase(get(), get()) }
            
        }
        
        
        val mapperModule = module {
            
            factory { InPlayerUserMapper()  }
            
            factory { MapAccessControlType() }
            
            factory { MapAccessFee(get(), get(), get(), get()) }
            
            factory { MapAccessType() }
            
            factory { MapInPlayerUser() }
            
            factory { MapItemAccess(get()) }
    
            factory { MapItemDetails(get(), get(), get()) }
            
            factory { MapItemType() }
            
            factory { MapSetupFee() }
            
            factory { MapTrialPeriod() }
            
            factory { InPlayerCredentialsMapper() }
            
            factory { AuthorizationModelMapper(get()) }
            
            //NOTIFICATION MAPPER
            
            factory { AccessGrantedNotificationMapper() }
            
            factory { AccessRevokedNotificationMapper() }
            
            factory { NotificationMapper(get(), get()) }
            
        }
        
        val notificationModule = module {
            single { AWSNotificationManager(get(), get()) }
        }
        
        startKoin(listOf(contextModule, mapperModule, configurationModule, dataModule, accountUseCaseModule,
                mainControllerModule, assetsUseCaseModule, notificationModule, paymentUseCaseModule))
        
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