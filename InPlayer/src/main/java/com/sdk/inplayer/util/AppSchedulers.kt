package com.sdk.inplayer.util

import com.sdk.domain.schedulers.InPlayerSchedulers
import io.reactivex.Scheduler


internal class AppSchedulers : InPlayerSchedulers {
    
    override val computation: Scheduler
        get() = io.reactivex.schedulers.Schedulers.computation()
    
    
    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()
    
    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
}