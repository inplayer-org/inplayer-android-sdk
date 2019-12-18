package com.sdk.inplayer.mapper

import com.google.gson.Gson
import com.sdk.data.remote.error.AuthTokenMissingException
import com.sdk.inplayer.model.error.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


internal object ThrowableToInPlayerExceptionMapper {
    
    fun mapThrowableToException(e: Throwable): InPlayerException {
        
        return when (e) {
            is AuthTokenMissingException -> {
                handleUnauthorizedUser(e)
            }
            is HttpException -> {
                if (e.code() == 401)
                    handleUnauthorizedUser(e)
                else {
                    val responseBody = e.response().errorBody()
                    InPlayerHttpException(e.code(), getErrorMessage(responseBody!!), e)
                }
            }
            is SocketTimeoutException, is UnknownHostException -> {
                InPlayerNetworkException(0, mutableListOf(e.localizedMessage), e)
            }
            is MalformedURLException -> {
                InPlayerMalformedUrlError(0, mutableListOf(e.localizedMessage), e)
            }
            else -> {
                InPlayerUnknownException(
                    0,
                    mutableListOf("Unknown exception occurred, Error: ${e.localizedMessage}"),
                    e
                )
            }
        }
        
    }
    
    private fun handleUnauthorizedUser(e: Throwable) =
        InPlayerUnauthorizedException(
            401,
            mutableListOf("User not authenticated, please authenticate first."),
            e
        )
    
    private fun getErrorMessage(responseBody: ResponseBody): List<String> {
        try {
            
            val jsonObject = JSONObject(responseBody.string())
            
            val errorObject = jsonObject.getJSONObject("errors")
            
            val errorsMap = Gson().fromJson(
                errorObject.toString(),
                HashMap::class.java
            ) as HashMap<String, String>
            
            val errorsMessages = mutableListOf<String>()
            
            for( (key, value) in errorsMap){
                errorsMessages.add(value)
            }
            
            return errorsMessages
        } catch (e: Exception) {
            return mutableListOf("${e.message}")
        }
        
    }
}