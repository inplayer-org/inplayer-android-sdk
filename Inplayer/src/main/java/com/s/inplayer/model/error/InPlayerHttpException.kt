package com.s.inplayer.model.error

/**
 * Created by victor on 1/3/19
 */
class InPlayerHttpException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList,e)