package com.s.inplayer

import com.s.inplayer.api.Account
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Created by victor on 12/25/18
 */
object InPlayerKotlin : KoinComponent {
    
    val Account: Account by inject()
    
}