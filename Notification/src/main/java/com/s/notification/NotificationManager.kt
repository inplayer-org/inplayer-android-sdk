package com.s.notification

import android.util.Log
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSSessionCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import com.s.notification.NotificationManager.Const.ACCES_KEY
import com.s.notification.NotificationManager.Const.ACCES_SESSION_KEY
import com.s.notification.NotificationManager.Const.SECRET_KEY
import java.io.UnsupportedEncodingException


/**
 * Created by victor on 1/14/19
 */
class NotificationManager {
    
    // Initialize the AWSIotMqttManager with the configuration
    init {
        initIotMqttManager()
    }
    
    object Const {
        
        val ACCES_KEY = "ASIAULR2BTKRA2J6VI6F"
        
        val SECRET_KEY = "x24X3aIJeFWNmymjXaSTLDD+2XpyoQrBwC5eZtKy"
        
        val ACCES_SESSION_KEY = "FQoGZXIvYXdzELH//////////wEaDNF9rqxgcsW7MTjXpSL0AV5n+l94Jhc0H0o0qvXpeMCIGNeKIp36rDVn9+ARbivKTnMAZRAiuh+enWkFWANpTQnOTYIPkxVrG11tFcCfy8UICHP+gE0dsVCbHg1sS36gCNowWinLaRsbRaCrPc8H3+P4WROWZvKIMgOpmpuEwViyPCtqDfd5d4Dgs556UY7WozCTJkJSJ4wiItpWxr2OY/XkLc7P7pC2v28wMpr16734+bmKJb9BgcDDeTzP0c2ZG2KKsu4RYAl+yEEOnCzR1m5IQSBs4R7QUxHfgtVrgRoGz24CEz/6BaKg6A9pa0zrdvQAI5RiodTSu5GiZr+ikgqUY98oj9fy4QU="
    }
    
    private lateinit var mqttManager: AWSIotMqttManager
    
    private fun initIotMqttManager() {
        
        mqttManager = AWSIotMqttManager(
                ACCES_KEY,
                "a3gkl64duktvc4.iot.eu-west-1.amazonaws.com")
        
        // mqttManager.setReconnectRetryLimits(1, 1)
        mqttManager.connect(StaticCredentialsProvider(MyAwsProvider())) { status: AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus?, throwable: Throwable? ->
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
    
    class MyAwsProvider : AWSCredentials, AWSSessionCredentials {
        
        override fun getSessionToken() = ACCES_SESSION_KEY
        
        override fun getAWSAccessKeyId() = ACCES_KEY
        
        override fun getAWSSecretKey() = SECRET_KEY
        
    }
}