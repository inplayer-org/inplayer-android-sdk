package com.sdk.domain.schedulers

import io.reactivex.Scheduler


interface InPlayerSchedulers {
    
    val subscribeOn: Scheduler
    
    val observeOn: Scheduler
    
    val computation: Scheduler
    
}