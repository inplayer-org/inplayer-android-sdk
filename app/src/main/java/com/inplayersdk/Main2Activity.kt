package com.inplayersdk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.s.inplayer.InPlayer
import com.s.inplayer.InPlayerKotlin
import com.s.inplayer.callback.InPlayerCallback
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity() {
    
    //val Account: Account by inject()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        
        InPlayer.initialize(InPlayer.Configuration.Builder(this).isStaging(true).build())
        
        val isLoggedIn = InPlayerKotlin.Account.isUserloggedIn()
        Log.v("TAG", "Is logged in $isLoggedIn")
        
        login.setOnClickListener {
            logInuser()
        }
        
        logout.setOnClickListener {
            logOut()
        }
        
        sign_up.setOnClickListener {
            signUp()
        }
        
        account_details.setOnClickListener {
            accountDetails()
        }
        
        erase.setOnClickListener {
            eraseUser()
        }
        
        changePassword.setOnClickListener {
            changePassword()
        }
        
        forgot_password.setOnClickListener {
            forgotPassword(BuildConfig.UUID, "victorpetrovski93+test4@gmai.com")
        }
        
        update.setOnClickListener {
            updateUser("HIKTOR Petrovski")
        }
    }
    
    private fun logInuser() {
        InPlayerKotlin.Account.authenticate("sdks@inplayer.com", "sdks123456", BuildConfig.UUID, InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                
            } else {
                //Handle Error
            }
        })
    }
    
    private fun logOut() {
        InPlayerKotlin.Account.logOut(InPlayerCallback { sucessMessage, error ->
        
        })
    }
    
    private fun signUp() {
        InPlayerKotlin.Account.signUp("Viktor Petrovski", "victorpetrovski93+test4@gmai.com", "androidsdk123", "androidsdk123", "7ad8a510-b720-4a18-aa38-0260e5fd1cb2", InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                Log.v("signUp", "User created ")
            } else {
                error.printStackTrace()
            }
        })
    }
    
    private fun accountDetails() {
        InPlayerKotlin.Account.accountDetails(InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("signUp", "User Details ")
            } else {
                //Handle Error
                error.printStackTrace()
            }
        })
    }
    
    private fun eraseUser() {
        InPlayerKotlin.Account.eraseUser("androidsdk123", InPlayerCallback { sucessMessage, error ->
        
        })
    }
    
    private fun changePassword() {
        InPlayerKotlin.Account.changePassword("newpassword12345", "newpassword12345", "newpassword123",
                InPlayerCallback { sucessMessage, error ->
                    if (error != null)
                        error.printStackTrace()
                })
    }
    
    private fun forgotPassword(merchantUUID: String, email: String) {
        InPlayerKotlin.Account.forgotPassword(merchantUUID, email, InPlayerCallback { sucessMessage, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("signUp", "User Details ")
            } else {
                //Handle Error
                error.printStackTrace()
            }
        })
    }
    
    private fun updateUser(fullName: String) {
        
        var map = HashMap<String, String>()
        map["country"] = "Germany"
        
        InPlayerKotlin.Account.updateUser(fullName, map, InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("signUp", "User Details  Updated")
            } else {
                //Handle Error
                error.printStackTrace()
            }
        })
    }
    
}
