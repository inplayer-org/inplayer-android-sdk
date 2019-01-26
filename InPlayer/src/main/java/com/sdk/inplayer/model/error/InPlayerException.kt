package com.sdk.inplayer.model.error


abstract class InPlayerException(val errorCode: Int, val errorsList: List<String>, val e: Throwable)