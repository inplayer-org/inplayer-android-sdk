package com.s.inplayer.callback.error

/**
 * Created by victor on 1/4/19
 */
class InPlayerInvalidInputException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList, e)