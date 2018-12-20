package com.s.domain.usecase.autehntication

import com.s.domain.schedulers.MySchedulers
import com.s.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by victor on 12/20/18
 */
class CreateAccountUseCase @Inject constructor(private val schedulers: MySchedulers)
    : SingleUseCase<CreateAccountUseCase.Params, Nothing>(schedulers) {
    
    override fun buildUseCaseObservable(params: Nothing?): Single<Params> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
    data class Params(val username: String)
}