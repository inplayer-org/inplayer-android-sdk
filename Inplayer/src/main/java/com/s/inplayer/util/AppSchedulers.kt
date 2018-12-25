package com.s.inplayer.util

import com.s.domain.schedulers.MySchedulers
import io.reactivex.Scheduler

/**
 * Created by victor on 12/23/18
 */
class AppSchedulers : MySchedulers {
    
    override val computation: Scheduler
        get() = io.reactivex.schedulers.Schedulers.computation()
    
    
    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()
    
    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
}