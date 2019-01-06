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
            forgotPassword(BuildConfig.UUID, "victorpetrovski93+test94@gmail.com")
        }
        
        update.setOnClickListener {
            accountDetails()
        }
        
        set_new_password.setOnClickListener {
            val password = "newpassword12345678"
            setNewPassword("94c2cd8235e3c3a9", password, password)
        }
        
        get_access.setOnClickListener {
            getAccess()
        }
        
        get_access_fee.setOnClickListener {
            getAccessFees()
        }
        
        get_item.setOnClickListener {
            getItem()
        }
    }
    
    private fun logInuser() {
        InPlayer.Account.authenticate("matej@inplayer.com", "matej123456", InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("logInuser", "User created $inPlayerUser")
                
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
        InPlayer.Account.signUp("Viktor Petrovski", "victorpetrovski93+test94@gmail.com", "androidsdk123", "androidsdk123", InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                Log.v("signUp", "User created $inPlayerUser")
            } else {
                //error.printStackTrace()
            }
        })
    }
    
    private fun accountDetails() {
        InPlayer.Account.accountDetails(InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("signUp", "User Details $inPlayerUser")
            } else {
                val errors = error.errorsList
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
                    //                    if (error != null)
//                        error.printStackTrace()
                })
    }
    
    private fun forgotPassword(merchantUUID: String, email: String) {
        InPlayer.Account.forgotPassword(merchantUUID, email, InPlayerCallback { sucessMessage, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("forgotPassword", "User Details ")
            } else {
                //Handle Error
                //  error.printStackTrace()
            }
        })
    }
    
    private fun updateUser(fullName: String) {
        
        var map = HashMap<String, String>()
        map["country"] = "Spain"
        
        
        InPlayer.Account.updateUser(fullName, map, InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("updateUser", "User Details  Updated $inPlayerUser")
            } else {
                //Handle Error
                // error.printStackTrace()
            }
        })
    }
    
    private fun setNewPassword(token: String, newPassword: String, newPasswordConfirmation: String) {
        InPlayer.Account.setupNewPassword(token, newPassword, newPasswordConfirmation, InPlayerCallback { message, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("setNewPassword", "User setNewPassword $message")
            } else {
                //Handle Error
                Log.v("setNewPassword", "Error block $message")
            }
        })
    }
    
    private fun getAccess() {
        InPlayer.Assets.getItemAccess(43871, InPlayerCallback { itemAccess, error ->
        
        })
    }
    
    private fun getItem() {
        InPlayer.Assets.getItemDetails(43871, InPlayerCallback { itemDetails, error ->
        
        })
    }
    
    private fun getAccessFees() {
        InPlayer.Assets.getAccessFees(43871, InPlayerCallback { accsFee, error ->
        
        })
    }
    
}
