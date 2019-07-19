package com.sdk.inplayer.model.error

/**
 * InPlayerMalformedUrlError
 * Created on 2019-05-02
 */
class InPlayerMalformedUrlError(errorCode: Int, errorsList: List<String>, e: Throwable) :
    InPlayerException(errorCode, errorsList, e)
