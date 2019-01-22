package com.sdk.domain.schedulers

import io.reactivex.Scheduler

/**
 * Created by victor on 12/20/18
 */
interface MySchedulers {
    
    val subscribeOn: Scheduler
    
    val observeOn: Scheduler
    
    val computation: Scheduler
    
}