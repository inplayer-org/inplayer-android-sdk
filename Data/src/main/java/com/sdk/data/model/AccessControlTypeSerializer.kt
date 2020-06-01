package com.sdk.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sdk.data.model.asset.AccessControlTypeModel
import java.lang.reflect.Type

private const val AUTH = "auth"

class AccessControlTypeSerializer : JsonDeserializer<AccessControlTypeModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AccessControlTypeModel {
        val jsonObject = json!!.asJsonObject
        
        val auth = if (jsonObject.get(AUTH).isJsonPrimitive) {
            when {
                jsonObject.get(AUTH).asJsonPrimitive.isBoolean -> {
                    jsonObject.get(AUTH).asBoolean
                }
                jsonObject.get(AUTH).asJsonPrimitive.isNumber -> {
                    jsonObject.get(AUTH).asNumber.toInt() == 1
                }
                else -> false
            }
        } else false
        
        return AccessControlTypeModel(jsonObject.get("id").asLong, jsonObject.get("name").asString, auth)
    }
}