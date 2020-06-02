package com.sdk.inplayer.di

import android.content.Context
import com.sdk.data.local.UserLocalAuthenticatorImpl
import com.sdk.data.model.mapper.*
import com.sdk.data.model.mapper.account.MapRegisterFields
import com.sdk.data.model.subscription.SubscriptionModel
import com.sdk.data.remote.*
import com.sdk.data.remote.api.InPlayerRemoteProvider
import com.sdk.data.remote.api.InPlayerRemotePublicProvider
import com.sdk.data.remote.api.InPlayerRemotePublicServiceAPI
import com.sdk.data.remote.api.InPlayerRemoteServiceAPI
import com.sdk.data.remote.refresh_token.InPlayerRemoteRefreshServiceAPI
import com.sdk.data.remote.refresh_token.InPlayerRemoteRefreshTokenProvider
import com.sdk.data.remote.refresh_token.RefreshTokenInterceptor
import com.sdk.data.repository.*
import com.sdk.data.repository.gateway.*
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.domain.entity.subscribtion.SubscriptionEntity
import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.gateway.InPlayerAssetsRepository
import com.sdk.domain.gateway.InPlayerPaymentRepository
import com.sdk.domain.gateway.InPlayerSubscriptionsRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.account.*
import com.sdk.domain.usecase.assets.GetAccessFeesUseCase
import com.sdk.domain.usecase.assets.GetItemAccessUseCase
import com.sdk.domain.usecase.assets.GetItemDetailsUseCase
import com.sdk.domain.usecase.authentication.*
import com.sdk.domain.usecase.payments.GetItemAccessListUseCase
import com.sdk.domain.usecase.payments.ValidateReceiptUseCase
import com.sdk.domain.usecase.subscription.GetSubscriptionsUseCase
import com.sdk.inplayer.api.*
import com.sdk.inplayer.configuration.InPlayer
import com.sdk.inplayer.mapper.MapInPlayerCollection
import com.sdk.inplayer.mapper.account.*
import com.sdk.inplayer.mapper.assets.*
import com.sdk.inplayer.mapper.notification.AccessGrantedNotificationMapper
import com.sdk.inplayer.mapper.notification.AccessRevokedNotificationMapper
import com.sdk.inplayer.mapper.notification.NotificationMapper
import com.sdk.inplayer.mapper.payment.CustomerAccessItemMapper
import com.sdk.inplayer.mapper.subscription.SubscriptionMapper
import com.sdk.inplayer.model.subscription.InPlayerSubscription
import com.sdk.inplayer.service.AccountService
import com.sdk.inplayer.service.AssetService
import com.sdk.inplayer.service.PaymentService
import com.sdk.inplayer.service.SubscriptionService
import com.sdk.inplayer.util.AppSchedulers
import com.sdk.inplayer.util.InPlayerSDKConfiguration
import com.sdk.notification.AWSNotificationManager
import com.sdk.notification.gateway.InPlayerAWSCredentialsRepository
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.setProperty


internal object InjectModules : KoinComponent {
    
    fun init(configuration: InPlayer.Configuration) {
        
        val contextModule = module {
            single<Context> { (getProperty(Const.context)) }
        }
        
        val configurationModule = module {
            single {
                InPlayerSDKConfiguration(
                    getProperty(Const.merchant_UUID),
                    configuration.referrer
                )
            }
        }
        
        //Data Module Mapper
        val dataMapper = module {
            
            factory { UserModelMapper() }
            
            factory { MapDataItemAccess() }
            
            factory { MapAWSCredentials() }
            
            factory { MapAuthorizationModel(get()) }
            
            factory { MapCustomerAccessItem() }
            
            factory { MapRegisterFields() }
            
            //Subscription MAPPER
            
            factory { MapSubscriptionModel() as ModelMapper<SubscriptionModel, SubscriptionEntity> }
            
            factory { MapCollectionModel<SubscriptionModel, SubscriptionEntity>(get()) }
        }
        
        // Dependency Injection for The Data Module
        val dataModule = module {
            
            single { AppSchedulers() as InPlayerSchedulers }
            
            
            //END Data Module Mapper
            
            factory<UserLocalAuthenticator> { UserLocalAuthenticatorImpl(get()) }
            
            //REFRESH TOKEN
            
            factory {
                InPlayerRemoteRefreshTokenProvider(
                    getProperty(Const.serverUrl),
                    configuration.isDebug
                ) as InPlayerRemoteRefreshServiceAPI
            }
            
            factory { RefreshTokenInterceptor(configuration.mMerchantUUID, get(), get()) }
            
            //END REFRESH TOKEN
            
            single {
                InPlayerRemotePublicProvider(
                    getProperty(Const.serverUrl),
                    configuration.isDebug
                ) as InPlayerRemotePublicServiceAPI
            }
            
            single {
                InPlayerRemoteProvider(
                    getProperty(Const.serverUrl),
                    configuration.isDebug,
                    get(),
                    get()
                ) as InPlayerRemoteServiceAPI
            }
            
            
            
            factory { AccountRemoteImpl(get(), get()) as AccountRemote }
            
            factory { AssetsRemoteImpl(get(), get()) as AssetsRemote }
            
            factory { NotificationsRemoteImpl(configuration.isDebug, get()) as NotificationsRemote }
            
            factory { PaymentsRemoteImpl(get()) as PaymentsRemote }
            
            factory { SubscriptionRemoteImpl(get()) as SubscriptionRemote }
            
            //REPOSITORY
            
            factory {
                InPlayerAssetsRepositoryImpl(
                    get(),
                    get()
                ) as InPlayerAssetsRepository
            }
            
            factory {
                InPlayerAccountRepositoryImpl(
                    get(),
                    get(),
                    get(),
                    get(),
                    get()
                ) as InPlayerAccountRepository
            }
            
            factory {
                InPlayerAWSCredentialsRepositoryImpl(
                    get(),
                    get(),
                    get()
                ) as InPlayerAWSCredentialsRepository
            }
            
            factory { InPlayerPaymentsRepositoryImpl(get(), get()) as InPlayerPaymentRepository }
            
            factory {
                InPlayerSubscriptionRepositoryImpl(
                    get(),
                    get()
                ) as InPlayerSubscriptionsRepository
            }
            
            //END REPOSITORY
            
        }
        
        val mainControllerModule = module {
            
            factory { Asset(get(), get(), get()) }
            
            factory {
                Account(
                    (getProperty(Const.context)),
                    get(),
                    get(),
                    get(),
                    get(),
                    get(),
                    get(),
                    get(),
                    get()
                )
            }
            
            factory { Notification(get(), get()) }
            
            factory { Payment(get(), get(), get()) }
            
            factory { Subscription(get(), get(), get()) }
            
        }
        
        val accountUseCaseModule = module {
            
            factory { AccountService() }
            
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
            
            factory { GetAccountUseCase(get()) }
            
            factory { ExportAccountDataUseCase(get(), get()) }
            
            factory { GetRegisterFieldsUseCase(get(), get()) }
            
            factory { GetSocialUrlsUseCase(get(), get()) }
            
            factory { ValidateSocialLoginUseCase(get(), get()) }
            
            factory { PinCodeVerificationUseCase(schedulers = get(),inPlayerAccountRepository = get()) }
        }
        
        val assetsUseCaseModule = module {
            
            factory { GetItemDetailsUseCase(get(), get()) }
            
            factory { GetAccessFeesUseCase(get(), get()) }
            
            factory { GetItemAccessUseCase(get(), get()) }
            
            factory { AssetService() }
            
        }
        
        val paymentUseCaseModule = module {
            
            factory { ValidateReceiptUseCase(get(), get()) }
            
            factory { PaymentService() }
            
            factory { GetItemAccessListUseCase(get(), get()) }
            
        }
        
        val subscriptionUseCaseModule = module {
            
            factory { GetSubscriptionsUseCase(get(), get()) }
            
            factory { SubscriptionService() }
        }
        
        
        val mapperModule = module {
            
            factory { InPlayerUserMapper() }
            
            factory { MapAccessControlType() }
            
            factory { MapAccessType() }
            
            factory { MapItemType() }
            
            factory { MapSetupFee() }
            
            factory { MapTrialPeriod() }
            
            factory { InPlayerCredentialsMapper() }
            
            factory { AuthorizationModelMapper(get()) }
            
            factory { RegisterFieldsMapper(get()) }
            
            factory { InPlayerSocialUrlsMapper() }
            
            //NOTIFICATION MAPPER
            
            factory { AccessGrantedNotificationMapper() }
            
            factory { AccessRevokedNotificationMapper() }
            
            factory { NotificationMapper(get(), get()) }
            
            
            //SubscribtionMapper
            
            factory { SubscriptionMapper() as DomainMapper<SubscriptionEntity, InPlayerSubscription> }
            
            factory { MapInPlayerCollection<SubscriptionEntity, InPlayerSubscription>(get()) }
            
            
            //PAYMENT MAPPER
            factory { CustomerAccessItemMapper() }
            
        }
        
        val notificationModule = module {
            single { AWSNotificationManager(get(), get()) }
        }
        
        startKoin(
            listOf(
                contextModule,
                mapperModule,
                configurationModule,
                dataModule,
                accountUseCaseModule,
                mainControllerModule,
                assetsUseCaseModule,
                notificationModule,
                paymentUseCaseModule,
                dataMapper,
                subscriptionUseCaseModule
            )
        )
        
        setProperty(Const.context, configuration.context)
        
        setProperty(Const.serverUrl, configuration.mServerUrl)
        
        setProperty(Const.merchant_UUID, configuration.mMerchantUUID)
        
        //setProperty(Const.referrer, configuration?.referrer)
        
    }
    
    object Const {
        
        val context = "context"
        
        val serverUrl = "server_url"
        
        val merchant_UUID = "merchant_uuid"
        
        val referrer = "referrer"
        
    }
}