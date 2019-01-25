package com.sdk.inplayer.model.error


class InPlayerNetworkException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList, e)