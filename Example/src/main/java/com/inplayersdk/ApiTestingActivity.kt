package com.inplayersdk

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inplayersdk.databinding.ActivityMain2Binding
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.callback.InPlayerNotificationCallback
import com.sdk.inplayer.configuration.InPlayer
import com.sdk.inplayer.model.account.RegisterFieldType
import com.sdk.inplayer.model.error.InPlayerException
import com.sdk.inplayer.model.notification.InPlayerNotification
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus

class ApiTestingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        binding.contentMain.login.setOnClickListener {
            logInuser()
        }

        binding.contentMain.logout.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putLong("TokenExpiresAt", 1516063572).apply()
        }

        binding.contentMain.signUp.setOnClickListener {
//            sendPinCode()
//            signUp()
            exportData()
        }

        binding.contentMain.accountDetails.setOnClickListener {
            accountDetails()
        }

        binding.contentMain.erase.setOnClickListener {
            eraseUser()
        }

        binding.contentMain.changePassword.setOnClickListener {
            changePassword()
        }

        binding.contentMain.forgotPassword.setOnClickListener {
            forgotPassword("frosina+sdk11111@inplayer.com", 663)
        }

        binding.contentMain.update.setOnClickListener {
            updateUser("HIKTORRRR")
        }

        binding.contentMain.setNewPassword.setOnClickListener {
            val password = "newpassword12345678"
            setNewPassword("e066f10e9192127a", password, password)
        }

        binding.contentMain.getAccess.setOnClickListener {
            getAccess()
        }

        binding.contentMain.getAccessFee.setOnClickListener {
            getAccessFees()
        }

        binding.contentMain.getItem.setOnClickListener {
            getItem()
        }

        binding.contentMain.getAccessFeeV2.setOnClickListener {
            initNotification()
        }

        binding.contentMain.publish.setOnClickListener {
            InPlayer.Account.getAccountInfo()
        }

        binding.contentMain.btnSubscriptions.setOnClickListener {
            getSubscriptions()
        }

        binding.contentMain.btnRefreshToken.setOnClickListener {
            InPlayer.Account.refreshToken(
                "def502001db88c34f019e16044833a8898158cf59f520b80bbf81a43fa8fa1b2f7c83dcb0de90d59d0058a143fc1043d956e204558703cf8ca022e45c19f7f51e00c77d98a75d19be8c335c9fd40e176feb021b0e923a5216157d74b6f1c3a869cc7b695929dd5945d188e0b73d1b5329870e3b2d7c50011d7f0fcaa8ae81f9df48039a78188c3bd6acadbab9025a53e4d9fb18b3e3808f63a37c4b42cfd581b383b87852261e7fdf79b2697361b28adaeaf8bea34f0ef11b72c1d36f894c2368f0fedae1f69ceaa2484af39d6c6e17b021c4627104ee930e7c92989937b57142b67106f4b74be0d1f7a0e15c8785a272aa34307f7a95b922a87e88859cf8b011f4074fccf988f1de0110e239f657a5f8a5a3c490dc052f40b186da17017a96fec2670a21d887ce91165ed04c3aaad51a508843d58c70463e6c2b58f3919581eef9909b8013715d59fdf67c10b358681e45ac01ad855537a4a966c22a9b418e1ccc6107768b97dbddfb3427b18e3205111caf53eac530601141254f67bf245f9c3a15ba9010e0425c1f5e942b0a49e76e8d71511a79b7b9fb2e233075173daa0585be7bce89921601e"
            ) { _, error ->
                if (error == null) {
                    //SUCCESS - Handle new token
                    Log.v("refreshToken Call", "--- Success Refresh --")
                } else {
                    //Handle Error
                    Log.v("refreshToken Call", "---Error--> $error")
                }
            }
        }

        binding.contentMain.btnSubscriptionsSubscription.setOnClickListener {
            getSubscriptionsFromSubscription()
        }

        binding.contentMain.isAuthenticated.setOnClickListener {
            Toast.makeText(
                this,
                "Is Authenticated is ${InPlayer.Account.isAuthenticated()}",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.contentMain.getAccessFeeV2.setOnClickListener {
            InPlayer.Assets.getAssetAccessFeesV2(58458, 1255, InPlayerCallback { accessFee, error ->
                if (error == null) {
                    Log.v(
                        "getAssetAccessFees",
                        "Access Fees: ${accessFee[1].item?.accessControlType?.auth}"
                    )
                } else {
                    //Handle Error
                    Log.v("getAssetAccessFees", "Error block $error")
                    error.e.printStackTrace()
                }
            })
        }

        binding.contentMain.btnPayment.setOnClickListener {
            getPaymentCall()
        }

        binding.contentMain.btnValidate.setOnClickListener {
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

        binding.contentMain.btnValidateProductName.setOnClickListener {
            getValidateByProductNameCall()
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

//    package main
//
//    import (
//    "encoding/json"
//    "fmt"
//    )
//
//    type MyJsonName struct {
//        State int `json:"purchase_state"`
//        PackageName string `json:"package_name"`
//        PurchaseToken string `json:"purchase_token"`
//    }
//
//    func main() {
//        jsonSrc := []byte(`{"product_id": "105974_24300","purchase_token": "lfigeacbnnaipilhadmiljmn.AO-J1OypRftg8_pqZ86VWTkiTrRNVIMK95dlIvcXEqOz7QiPVtxvjGQ6BAYe_z7MOuHxxo3Ce_3P7b1Wd9xRDmZ0uD0Ii5JzQwsulwPXbZLPOmwoXha9ado","package_name": "com.inplayer.paywalltest","order_id": "GPA.3396-6118-6019-43012","purchase_state": 2}`)
//
//        var myJson MyJsonName
//        json.Unmarshal(jsonSrc, &myJson)
//
//        fmt.Println(myJson)
//    }

    private fun getValidateByProductNameCall() {
        val purchaseObject =
            "{\"productId\": \"105982_24307\",\"purchaseToken\": \"lbeleljipnikkjnobnamacjm.AO-J1OyLQUHCTffEnY_CygBdkq3MfxupPOcGnZxzjPqLfbGZmeg5dh-PRJLJYwSJodNw1mWpujcdz2vbbVe4c0yb3nJ0CyzDhguDPJfTUYDhfE13CSzSGi4\",\"packageName\": \"com.inplayer.paywalltest\",\"orderId\": \"GPA.3313-9219-0430-15154\",\"purchaseState\": 2,\"autoRenewing\": true,\"acknowledged\": false,\"purchaseTime\": 1636213219000}"



        InPlayer.Payment.validateByProductName(
            purchaseObject,
            "105982_24307   ",
            838,
            InPlayerCallback { validate, error ->
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
            "android-elena-002@mail.com",
            "test1234",
            InPlayerCallback { inPlayerUser, error ->
                if (error == null) {
                    //SUCCESS - Handle InPlayerUser
                }
            })
    }

    private fun getSubscriptions() {
        InPlayer.Account.getRegisterFields(InPlayerCallback { list, exception ->
            list.forEach {
                Log.v("Register Fields", "This is it $it")

                when (it.type) {
                    is RegisterFieldType.Country -> {
                        (it.type as RegisterFieldType.Country).options.forEach { _ ->

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
        InPlayer.Account.requestNewPassword(
            email,
            brandingId,
            InPlayerCallback { sucessMessage, error ->
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
