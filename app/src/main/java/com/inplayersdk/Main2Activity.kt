package com.inplayersdk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.s.inplayer.InPlayer
import com.s.inplayer.callback.InPlayerCallback
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity() {
    
    //val Account: Account by inject()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        
        //InPlayer.initialize(InPlayer.Configuration.Builder(BuildConfig.UUID, "referr").isStaging(true).build())
        
        val isLoggedIn = InPlayer.Account.isUserloggedIn()
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
            accountDetails()
            updateUser("HIKTOR Petrovski")
        }
        
        set_new_password.setOnClickListener {
        
        }
    }
    
    private fun logInuser() {
        InPlayer.Account.authenticate("sdks@inplayer.com", "sdks123456", InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                
            } else {
                //Handle Error
            }
        })
    }
    
    private fun logOut() {
        InPlayer.Account.logOut(InPlayerCallback { sucessMessage, error ->
        
        })
    }
    
    private fun signUp() {
        InPlayer.Account.signUp("Viktor Petrovski", "victorpetrovski93+test455@gmai.com", "androidsdk123", "androidsdk123", InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                Log.v("signUp", "User created ")
            } else {
                error.printStackTrace()
            }
        })
    }
    
    private fun accountDetails() {
        InPlayer.Account.accountDetails(InPlayerCallback { inPlayerUser, error ->
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
        InPlayer.Account.eraseUser("androidsdk123", InPlayerCallback { sucessMessage, error ->
        
        })
    }
    
    private fun changePassword() {
        InPlayer.Account.changePassword("newpassword12345", "newpassword12345", "newpassword123",
                InPlayerCallback { sucessMessage, error ->
                    if (error != null)
                        error.printStackTrace()
                })
    }
    
    private fun forgotPassword(merchantUUID: String, email: String) {
        InPlayer.Account.forgotPassword(merchantUUID, email, InPlayerCallback { sucessMessage, error ->
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
        map["country"] = "Spain"
        
        
        InPlayer.Account.updateUser(fullName, map, InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("signUp", "User Details  Updated")
            } else {
                //Handle Error
                error.printStackTrace()
            }
        })
    }
    
    private fun setNewPassword(token: String, newPassword: String, newPasswordConfirmation: String) {
        InPlayer.Account.setupNewPassword(token, newPassword, newPasswordConfirmation, InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("setNewPassword", "User setNewPassword")
            } else {
                //Handle Error
                error.printStackTrace()
            }
        })
    }
    
}
