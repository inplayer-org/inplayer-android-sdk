package com.sdk.domain.usecase.base

import com.sdk.domain.schedulers.InPlayerSchedulers
import io.reactivex.Single

/**
 * Created by victor on 12/20/18
 */
abstract class SingleUseCase<T, in Params> constructor(private val inPlayerSchedulers : InPlayerSchedulers) {
    
    protected abstract fun buildUseCaseObservable(params: Params? = null) : Single<T>
    
    open fun execute(params : Params? = null) : Single<T>{
        return buildUseCaseObservable(params)
                .subscribeOn(inPlayerSchedulers.subscribeOn)
                .observeOn(inPlayerSchedulers.observeOn)
    }
    

}