package com.inplayersdk

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
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
            PreferenceManager.getDefaultSharedPreferences(this).edit().putLong("TokenExpiresAt", 1516063572).apply()
        }
        
        sign_up.setOnClickListener {
//            sendPinCode()
//            signUp()
            exportData()
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
            forgotPassword("frosina+sdk11111@inplayer.com", 663)
        }
        
        update.setOnClickListener {
            updateUser("HIKTORRRR")
        }
        
        set_new_password.setOnClickListener {
            val password = "newpassword12345678"
            setNewPassword("e066f10e9192127a", password, password)
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
        
        get_access_fee_v2.setOnClickListener {
            initNotification()
        }
        
        publish.setOnClickListener {
            val account = InPlayer.Account.getAccountInfo()
        }
        
        btn_subscriptions.setOnClickListener {
            getSubscriptions()
        }
    
        btn_subscriptions_subscription.setOnClickListener {
            getSubscriptionsFromSubscription()
        }
        
        isAuthenticated.setOnClickListener {
            Toast.makeText(this,"Is Authenticated is ${InPlayer.Account.isAuthenticated()}", Toast.LENGTH_LONG).show()
        }
    
        get_access_fee_v2.setOnClickListener {
            InPlayer.Assets.getAssetAccessFeesV2(58458, 1255, InPlayerCallback { accessFee, error ->
                if (error == null) {
                    Log.v("getAssetAccessFees", "Access Fees: ${accessFee[1].item?.accessControlType?.auth}")
                } else {
                    //Handle Error
                    Log.v("getAssetAccessFees", "Error block $error")
                    error.e.printStackTrace()
                }
            })
        }
        
        btn_payment.setOnClickListener {
            getPaymentCall()
        }

        btn_validate.setOnClickListener {
            InPlayer.Payment.validate("active", "",
                InPlayerCallback { _, error ->
                    if (error == null) {
                        //SUCCESS - Handle Payment Receipt
                        Log.v("Validates Call", "--- Success In App purchase from Google Play --")
                    } else {
                        //Handle Error
                        Log.v("Validates Call", "---Error--> $error")
                    }
                })
        }
    }

    private fun exportData() {

        InPlayer.Account.exportData("newpassword12345678", 663,
            InPlayerCallback { _, error ->
                if (error == null) {
                    //SUCCESS - Handle InPlayerUser
                    Log.v("Payment Call", "---Success--")
                } else {
                    //Handle Error
                    Log.v("Payment Call", "---Error--> $error")
                }
            })
    }

    private fun getPaymentCall() {
        InPlayer.Payment.getPurchaseHistory("active", 0, 10, null,
            InPlayerCallback { merchantSubscriptionRecords, error ->
            if (error == null) {
                //SUCCESS - Handle InPlayerUser
                Log.v("Payment Call", "---Success--")
            } else {
                //Handle Error
                Log.v("Payment Call", "---Error--> $error")
            }
        })
    }
    
    private fun logInuser() {
        InPlayer.Account.authenticate(
            "frosina+sdk11111@inplayer.com",
            "newpassword12345678",
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
    
    
    private fun getSubscriptionsFromSubscription() {
        InPlayer.Subscription.getSubscriptions(0, 200, InPlayerCallback { response, ex ->
            response.collection.forEach {
    
                Log.v("Register Fields", "This is it $it")
            }
        })
    }
    
    private fun logOut() {
        InPlayer.Account.signOut(InPlayerCallback { sucessMessage, error -> })
    }
    
    private fun signUp() {
        InPlayer.Account.signUp(
            "FrosinaT5",
            "Test105",
            "frosina+sdk11111@inplayer.com",
            "androidsdk123",
            "androidsdk123",
            663,

            InPlayerCallback { inPlayerUser, error ->
                if (error == null) {
                    Log.v("signUp", "User created $inPlayerUser")
                } else {
                    //error.printStackTrace()
                }
            })
    }

    private fun sendPinCode() {
        InPlayer.Account.sendPinCode(663) { data, error ->
            if (error == null) {
                Log.v(
                    "sendPinCode", "PinCodeVerification $data"
                )
            } else {
                //error.printStackTrace()
            }
        }
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
            "test1234",
            663,
            InPlayerCallback { sucessMessage, error -> })
    }
    
    private fun changePassword() {
        InPlayer.Account.changePassword("androidsdk123", "test1234", "test1234", 663,
            InPlayerCallback { sucessMessage, error ->
                
            })
    }
    
    private fun forgotPassword(email: String, brandingId: Int? = null) {
        InPlayer.Account.requestNewPassword(email, brandingId, InPlayerCallback { sucessMessage, error ->
            if (error == null) {
                //Handle InPlayerUser
                Log.v("requestNewPassword", sucessMessage.toString())
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
            663,
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
        InPlayer.Assets.checkAccessForAsset(75929, InPlayerCallback { itemAccess, error ->
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
        InPlayer.Assets.getAsset(58458, InPlayerCallback { inPlayerItem, error ->
            if (error == null) {
                Log.v("getAccess", "Item: ${inPlayerItem.accessControlType?.auth}")
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
