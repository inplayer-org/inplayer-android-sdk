package com.inplayersdk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.s.inplayer.InPlayer
import com.s.inplayer.api.Account
import kotlinx.android.synthetic.main.activity_main2.*
import org.koin.android.ext.android.inject

class Main2Activity : AppCompatActivity() {
    
    val Account: Account by inject()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        
        
        InPlayer.initialize(InPlayer.Configuration.Builder(this).isStaging(true).build())

//
//        Account.authenticate("sdks@inplayer.com", "sdks123456", BuildConfig.UUID, InPlayerCallback { inPlayerUser, error ->
//            if (error == null) {
//                //Handle InPlayerUser
//
//            } else {
//                //Handle Error
//            }
//        })
//
        
        val isLoggedIn = Account.isUserloggedIn()
        Log.v("TAG", "Is logged in $isLoggedIn")
        
        
    }
    
}
