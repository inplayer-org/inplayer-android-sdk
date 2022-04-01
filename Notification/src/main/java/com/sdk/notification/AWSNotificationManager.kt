package com.sdk.notification

import android.util.Log
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSSessionCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import com.sdk.domain.schedulers.InPlayerSchedulers
import com.sdk.notification.BuildConfig.AWS_IOT_ENDPOINT
import com.sdk.notification.entity.InPlayerAWSCredentials
import com.sdk.notification.gateway.InPlayerAWSCredentialsRepository
import com.sdk.notification.modelparser.InPlayerNotificationParser
import io.reactivex.disposables.Disposable
import java.io.UnsupportedEncodingException


class AWSNotificationManager(var inPlayerAWSCredentialsRepository: InPlayerAWSCredentialsRepository, val appSchedulers: InPlayerSchedulers) {
    
    private lateinit var callback: AWSNotificationCallback
    
    private var mqttManager: AWSIotMqttManager? = null
    
    private lateinit var inPlayerAWSCredentials: InPlayerAWSCredentials
    
    public var isSubscribed = false
    
    fun subscribe(callback: AWSNotificationCallback) {
        this.callback = callback
        
        //Check if the user is already subscribed
        if (isSubscribed) {
            Log.i("AWSNotificationManager", "Already subscribed")
            return
        }
        
        initIotMqttManager()
        
        isSubscribed = true
    }
    
    fun disconnect() {
        mqttManager?.let {
            it.disconnect()
        }
        subscribe?.dispose()
        isSubscribed = false
    }
    
    var subscribe: Disposable? = null
    
    private fun initIotMqttManager() {
        subscribe = inPlayerAWSCredentialsRepository.getAwsCredentials()
                .observeOn(appSchedulers.observeOn)
                .subscribeOn(appSchedulers.subscribeOn)
                .subscribe({
                    inPlayerAWSCredentials = it
                    configureConnectingWithIoT()
                }, {
                    callback.onError(it)
                    isSubscribed = false
                })
        
    }
    
    private fun configureConnectingWithIoT() {
        mqttManager = AWSIotMqttManager(inPlayerAWSCredentials.accessKey, AWS_IOT_ENDPOINT)
        Log.i("Notif.ConnectingWithIoT", "MyAwsProvider accessKey--> " + inPlayerAWSCredentials.accessKey)
        Log.i("Notif.ConnectingWithIoT", "MyAwsProvider secretKey--> " + inPlayerAWSCredentials.secretKey)
        Log.i("Notif.ConnectingWithIoT", "MyAwsProvider sessionToken--> " + inPlayerAWSCredentials.sessionToken)

        mqttManager?.connect(StaticCredentialsProvider(MyAwsProvider(inPlayerAWSCredentials.accessKey, inPlayerAWSCredentials.secretKey, inPlayerAWSCredentials.sessionToken))) { status: AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus?, throwable: Throwable? ->
            throwable?.let {
                callback.onError(it)
                it.printStackTrace()
                isSubscribed = false
            }
            
            status?.let {
                Log.i("Notif.AWSClientStatus", "AWSIotMqttClientStatus: --> " + it)

                if (it == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {

                    subscribeToTopic()
                }
                callback.onStatusChanged(status.name)
            }
        }
    }
    
    
    private fun subscribeToTopic() {

//        Log.i("Notif.subscribeToTopic", "subscribeToTopic userUUID: --> " + inPlayerAWSCredentials.userUUID)
//        Log.i("Notif.subscribeToTopic", "subscribeToTopic QOS0: --> " + AWSIotMqttQos.QOS0)
        mqttManager?.subscribeToTopic(inPlayerAWSCredentials.userUUID, AWSIotMqttQos.QOS0) { _: String?, data: ByteArray? ->
            try {
                val message = String(data!!)
                Log.i("Notif.subscribeToTopic", "MESAGE: --> $message")
//                Log.d("AWSNotif.Manager", InPlayerNotificationParser.parseJSON(message).toString())
                callback.onMessageReceived(InPlayerNotificationParser.parseJSON(message))
            } catch (e: UnsupportedEncodingException) {
                Log.w("Notif.error", "ex: $e")
                callback.onError(UnsupportedEncodingException())
            }
        }
    }
    
    
    private class MyAwsProvider(private val accessKey: String, private val secretKey: String, private val accessSessionKey: String)
        : AWSCredentials, AWSSessionCredentials {
        
        override fun getSessionToken() = accessSessionKey
        
        override fun getAWSAccessKeyId() = accessKey
        
        override fun getAWSSecretKey() = secretKey
        
    }
}