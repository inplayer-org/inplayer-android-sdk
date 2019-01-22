package com.sdk.inplayer.model.notification


data class InPlayerAccountDeactivatedNotification(override val type: String, override val timestamp: Long) : InPlayerNotification