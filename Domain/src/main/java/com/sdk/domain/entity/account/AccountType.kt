package com.sdk.domain.entity.account


enum class AccountType(val type: String) {
    CONSUMER("consumer"),
    MERCHANT("merchant"),
    IN_PLAYER("inplayer")
}