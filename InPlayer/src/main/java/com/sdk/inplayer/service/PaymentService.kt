package com.sdk.inplayer.service

import com.sdk.domain.usecase.payments.GetItemAccessListUseCase
import com.sdk.domain.usecase.payments.ValidateReceiptUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class PaymentService : KoinComponent {
    
    val validateReceiptUseCase: ValidateReceiptUseCase by inject()
    
    val getItemAccessListUseCase: GetItemAccessListUseCase by inject()
}