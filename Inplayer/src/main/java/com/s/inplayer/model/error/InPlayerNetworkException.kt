package com.s.inplayer.model.error

/**
 * Created by victor on 1/4/19
 */
class InPlayerNetworkException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList,e)