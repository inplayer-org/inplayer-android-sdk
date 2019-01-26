package com.sdk.inplayer.model.error


class InPlayerUnauthorizedException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList,e)