package com.sdk.inplayer.model.notification


enum class InPlayerNotificationStatus {
    Unknown,
    Connecting,
    Connected,
    Disconnected,
    ConnectionRefused,
    ConnectionError,
    ProtocolError
}