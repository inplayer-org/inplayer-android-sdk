package com.inplayersdk

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.callback.InPlayerNotificationCallback
import com.sdk.inplayer.configuration.InPlayer
import com.sdk.inplayer.model.account.RegisterFieldType
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class ApiTestingActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        
        
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
            forgotPassword("victorpetrovski93+test94@gmail.com")
        }
        
        update.setOnClickListener {
            updateUser("HIKTORRRR")
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
        
        notification.setOnClickListener {
            initNotification()
        }
        
        publish.setOnClickListener {
            val account = InPlayer.Account.getAccountInfo()
        }
        
        
        btn_subscriptions.setOnClickListener {
            getSubscriptions()
        }
        
        btn_get_social_urls.setOnClickListener {
            getSocialUrls()
        }
    }
    
    private fun getSocialUrls() {
    
    
    }
    
    
    private fun logInuser() {
        InPlayer.Account.authenticate(
            "matej@inplayer.com",
            "matej123456",
            InPlayerCallback { inPlayerUser, error ->
                if (error == null) {
                    //SUCCESS - Handle InPlayerUser
                } else {
                    //Handle Error
                }
            })
    }
    
    private fun getSubscriptions() {
        
        InPlayer.Account.getRegisterFields(InPlayerCallback { list, exception ->
            list.forEach {
                Log.v("Register Fields", "This is it $it")
                
                when (it.type) {
                    is RegisterFieldType.Country -> {
                        (it.type as RegisterFieldType.Country).options.forEach {
                        
                        }
                    }
                }
            }
        })
    }
    
    private fun logOut() {
        InPlayer.Account.signOut(InPlayerCallback { sucessMessage, error -> })
    }
    
    private fun signUp() {
        InPlayer.Account.signUp("Viktor Petrovski",
            "victorpetrovski93+test99541@gmail.com",
            "androidsdk123",
            "androidsdk123", InPlayerCallback { inPlayerUser, error ->
                if (error == null) {
                    Log.v("signUp", "User created $inPlayerUser")
                } else {
                    //error.printStackTrace()
                }
            })
    }
    
    private fun accountDetails() {
        InPlayer.Account.getAccount(InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("accountDetails", "User Details: $inPlayerUser")
            } else {
                error.errorsList
                error.e.printStackTrace()
            }
        })
    }
    
    private fun eraseUser() {
        InPlayer.Account.deleteAccount(
            "androidsdk123",
            InPlayerCallback { sucessMessage, error -> })
    }
    
    private fun changePassword() {
        InPlayer.Account.changePassword("newpassword12345", "newpassword12345", "newpassword123",
            InPlayerCallback { sucessMessage, error ->
                
            })
    }
    
    private fun forgotPassword(email: String) {
        InPlayer.Account.requestNewPassword(email, InPlayerCallback { sucessMessage, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("requestNewPassword", sucessMessage)
            } else {
                //Handle Error
                //  error.printStackTrace()
            }
        })
    }
    
    private fun updateUser(fullName: String) {
        val map = HashMap<String, String>()
        map["country"] = "Spain"
        
        InPlayer.Account.updateAccount(fullName, map, InPlayerCallback { inPlayerUser, error ->
            if (error == null) {
                Log.v("updateAccount", "$inPlayerUser")
            } else {
                //Handle Error
                error.e.printStackTrace()
            }
        })
    }
    
    private fun setNewPassword(
        token: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        InPlayer.Account.setNewPassword(
            token,
            newPassword,
            newPasswordConfirmation,
            InPlayerCallback { message, error ->
                if (error == null) {
                    Log.v("setNewPassword", "User setNewPassword: $message")
                } else {
                    //Handle Error
                    Log.v("setNewPassword", "Error block $message")
                }
            })
    }
    
    private fun getAccess() {
        InPlayer.Assets.checkAccessForAsset(43871, InPlayerCallback { itemAccess, error ->
            if (error == null) {
                Log.v("getAccess", "Access: $itemAccess")
            } else {
                //Handle Error
                Log.v("getAccess", "Error block $error")
                error.e.printStackTrace()
            }
        })
    }
    
    private fun getItem() {
        InPlayer.Assets.getAsset(1111, InPlayerCallback { inPlayerItem, error ->
            if (error == null) {
                //SUCCESS - Handle InPlayerItem
            } else {
                //Handle Error
                Log.v("getItem", "Error block $error")
                error.e.printStackTrace()
            }
        })
    }
    
    private fun getAccessFees() {
        InPlayer.Assets.getAssetAccessFees(43871, InPlayerCallback { accsFee, error ->
            if (error == null) {
                Log.v("getAssetAccessFees", "Access Fees: $accsFee")
            } else {
                //Handle Error
                Log.v("getAssetAccessFees", "Error block $error")
                error.e.printStackTrace()
            }
        })
    }
    
    private fun initNotification() {
    
    }
    
    override fun onResume() {
        super.onResume()
        
        InPlayer.Notification.subscribe(object : InPlayerNotificationCallback {
            
            override fun onStatusChanged(status: InPlayerNotificationStatus) {}
            
            override fun onMessageReceived(message: InPlayerNotification) {}
            
            override fun onError(t: InPlayerException) {}
        })
    }
    
}
