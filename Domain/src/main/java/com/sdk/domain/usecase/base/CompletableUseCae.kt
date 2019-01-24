package com.sdk.domain.usecase.base

import com.sdk.domain.schedulers.InPlayerSchedulers
import io.reactivex.Completable

/**
 * Created by victor on 12/26/18
 */
abstract class CompletableUseCae<in Params> constructor(private val inPlayerSchedulers: InPlayerSchedulers) {
    
    protected abstract fun buildUseCaseObservable(params: Params? = null): Completable
    
    open fun execute(params: Params? = null): Completable {
        return buildUseCaseObservable(params)
                .subscribeOn(inPlayerSchedulers.subscribeOn)
                .observeOn(inPlayerSchedulers.observeOn)
    }
    
    
}