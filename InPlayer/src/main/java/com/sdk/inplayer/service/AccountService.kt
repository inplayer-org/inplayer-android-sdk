package com.sdk.inplayer.service

import com.sdk.domain.usecase.authentication.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class AccountService : KoinComponent {
    
    val createAccountUseCase: CreateAccountUseCase by inject()
    
    val authenticatedUseCase: AuthenticateUserUseCase by inject()
    
    val logOutUserUseCase: LogOutUserUseCase by inject()
    
    val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase by inject()
    
    val accountDetailsUseCase: AccountDetailsUseCase by inject()
    
    val eraseUserUseCase: EraseUserUseCase by inject()
    
    val changePasswordUseCase: ChangePasswordUseCase by inject()
    
    val forgotPasswordUseCase: ForgotPasswordUseCase by inject()
    
    val updateUserUseCase: UpdateUserUseCase by inject()
    
    val setNewPasswordUseCase: SetNewPasswordUseCase by inject()
    
    val credentialsUseCase: CredentialsUseCase by inject()
    
    val getAccountUseCase: GetAccountUseCase by inject()
    
    val exportAccountDataUseCase: ExportAccountDataUseCase by inject()
    
    val getRegisterFieldsUseCase: GetRegisterFieldsUseCase by inject()
    
    
}