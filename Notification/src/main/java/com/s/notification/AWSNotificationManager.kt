package com.s.notification

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSSessionCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import com.s.domain.schedulers.MySchedulers
import com.s.notification.entity.MyAWSCredentials
import com.s.notification.gateway.InPlayerAWSCredentialsRepository
import com.s.notification.modelparser.InPlayerNotificationParser
import java.io.UnsupportedEncodingException


/**
 * Created by victor on 1/14/19
 */
class AWSNotificationManager(var inPlayerAWSCredentialsRepository: InPlayerAWSCredentialsRepository, val appSchedulers: MySchedulers) {
    
    private lateinit var callback: AWSNotificationCallback
    
    private lateinit var mqttManager: AWSIotMqttManager
    
    private lateinit var myAWSCredentials: MyAWSCredentials
    
    fun subscribe(callback: AWSNotificationCallback) {
        this.callback = callback
        initIotMqttManager()
    }
    
    private fun initIotMqttManager() {
        
        inPlayerAWSCredentialsRepository.getAwsCredentials()
                .observeOn(appSchedulers.observeOn)
                .subscribeOn(appSchedulers.subscribeOn)
                .subscribe({
                    myAWSCredentials = it
                    configureConnectingWithIoT()
                }, {
                    callback.onError(it)
                })
        
    }
    
    private fun configureConnectingWithIoT() {
        mqttManager = AWSIotMqttManager(
                myAWSCredentials.accessKey,
                myAWSCredentials.iotEndpoint)
        
        mqttManager.connect(StaticCredentialsProvider(MyAwsProvider(myAWSCredentials.accessKey, myAWSCredentials.secretKey, myAWSCredentials.sessionToken))) { status: AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus?, throwable: Throwable? ->
            throwable?.let {
                callback.onError(it)
                it.printStackTrace()
            }
            
            status?.let {
                if (it == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {
                    subscribeToTopic()
                }
                callback.onStatusChanged(status.name)
            }
        }
    }
    
    
    private fun subscribeToTopic() {
        mqttManager.subscribeToTopic(myAWSCredentials.userUUID, AWSIotMqttQos.QOS0) { topic: String?, data: ByteArray? ->
            try {
                val message = String(data!!)
                callback.onMessageReceived(InPlayerNotificationParser.parseJSON(message))
            } catch (e: UnsupportedEncodingException) {
                callback.onError(UnsupportedEncodingException())
            }
        }
    }
    
    
    fun publish() {
    
    }
    
    private class MyAwsProvider(private val accessKey: String, private val secretKey: String, private val accessSessionKey: String)
        : AWSCredentials, AWSSessionCredentials {
        
        override fun getSessionToken() = accessSessionKey
        
        override fun getAWSAccessKeyId() = accessKey
        
        override fun getAWSSecretKey() = secretKey
        
    }
}