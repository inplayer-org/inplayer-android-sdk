package com.sdk.inplayer.model.error

class InPlayerUnknownException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList, e)