package com.s.domain.usecase.base

import com.s.domain.schedulers.MySchedulers
import io.reactivex.Completable

/**
 * Created by victor on 12/26/18
 */
abstract class CompletableUseCae<in Params> constructor(private val mySchedulers: MySchedulers) {
    
    protected abstract fun buildUseCaseObservable(params: Params? = null): Completable
    
    open fun execute(params: Params? = null): Completable {
        return buildUseCaseObservable(params)
                .subscribeOn(mySchedulers.subscribeOn)
                .observeOn(mySchedulers.observeOn)
    }
    
    
}