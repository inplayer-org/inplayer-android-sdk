package com.s.inplayer.model.error

/**
 * Created by victor on 1/3/19
 */
abstract class InPlayerException(val errorCode: Int, val errorsList: List<String>, val e: Throwable)