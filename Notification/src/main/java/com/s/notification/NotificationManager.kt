package com.s.notification

import android.util.Log
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSSessionCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import com.s.domain.schedulers.MySchedulers
import com.s.notification.entity.MyAWSCredentials
import com.s.notification.gateway.InPlayerAWSCredentialsRepository
import java.io.UnsupportedEncodingException


/**
 * Created by victor on 1/14/19
 */
class NotificationManager(var inPlayerAWSCredentialsRepository: InPlayerAWSCredentialsRepository, val appSchedulers: MySchedulers) {
    
    // Initialize the AWSIotMqttManager with the configuration
    init {
        initIotMqttManager()
    }
    
    private lateinit var mqttManager: AWSIotMqttManager
    
    private lateinit var myAWSCredentials: MyAWSCredentials
    
    private fun initIotMqttManager() {
        
        inPlayerAWSCredentialsRepository.getAwsCredentials()
                .observeOn(appSchedulers.observeOn)
                .subscribeOn(appSchedulers.subscribeOn)
                .subscribe({
                    myAWSCredentials = it
                    configureConnectingWithIoT()
                }, {
                    it.printStackTrace()
                })
        
    }
    
    private fun configureConnectingWithIoT() {
        mqttManager = AWSIotMqttManager(
                myAWSCredentials.accessKey,
                myAWSCredentials.iotEndpoint)
        
        mqttManager.connect(StaticCredentialsProvider(MyAwsProvider(myAWSCredentials.accessKey, myAWSCredentials.secretKey, myAWSCredentials.sessionToken))) { status: AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus?, throwable: Throwable? ->
            throwable?.let {
                it.printStackTrace()
                Log.v("NotificationManager", "Error :$it")
            }
            
            status?.let {
                Log.v("NotificationManager", "Success $it")
                if (it.name == "Connected") {
                    Log.v("NotificationManager", "Success CONNECTED $it")
                    subscribeToTopic()
                }
            }
        }
    }
    
    
    private fun subscribeToTopic() {
        mqttManager.subscribeToTopic("a7b9760d-5eee-4a3a-9ed1-36dea673a369", AWSIotMqttQos.QOS0) { topic: String?, data: ByteArray? ->
            try {
                val message = String(data!!)
                Log.d("NotificationManager", "Message received: $message")
            } catch (e: UnsupportedEncodingException) {
                Log.e("NotificationManager", "Message encoding error: ", e)
            }
            
        }
    }
    
    fun call() {
    }
    
    class MyAwsProvider(private val accessKey: String, private val secretKey: String, private val accessSessionKey: String) : AWSCredentials, AWSSessionCredentials {
        
        override fun getSessionToken() = accessSessionKey
        
        override fun getAWSAccessKeyId() = accessKey
        
        override fun getAWSSecretKey() = secretKey
        
    }
}