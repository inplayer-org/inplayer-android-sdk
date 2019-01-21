package com.s.inplayer.model.notification

/**
 * Created by victor on 1/21/19
 */
enum class InPlayerNotificationStatus {
    Unknown,
    Connecting,
    Connected,
    Disconnected,
    ConnectionRefused,
    ConnectionError,
    ProtocolError
}