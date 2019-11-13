package com.sdk.domain.usecase.account

import com.sdk.domain.gateway.InPlayerAccountRepository
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.domain.usecase.base.CompletableUseCae
import io.reactivex.Completable

class PinCodeVerificationUseCase(
    schedulers: InPlayerSchedulers,
    private val inPlayerAccountRepository: InPlayerAccountRepository
): CompletableUseCae<PinCodeVerificationUseCase.PinCodeInput>(schedulers) {
    
    override fun buildUseCaseObservable(params: PinCodeInput?): Completable {
        params?.let {
            return when(it){
                is PinCodeInput.ValidatePinCode -> inPlayerAccountRepository.validatePinCode(pinCode = it.pinCode)
                is PinCodeInput.SendPinCode -> inPlayerAccountRepository.sendPinCode(brandingId = it.brandingId)
            }
        }
        throw IllegalStateException("PinCodeVerificationUseCase can't be executed without params")
    }
    
    sealed class PinCodeInput{
        data class ValidatePinCode(val pinCode: String): PinCodeInput()
        data class SendPinCode(val brandingId: String? = null): PinCodeInput()
    }
}
