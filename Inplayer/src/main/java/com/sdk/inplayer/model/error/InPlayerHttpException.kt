package com.sdk.inplayer.model.error


class InPlayerHttpException(errorCode: Int, errorsList: List<String>, e: Throwable) : InPlayerException(errorCode, errorsList,e)