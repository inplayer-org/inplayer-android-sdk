package com.s.domain.entity.account

/**
 * Created by victor on 12/20/18
 */
enum class AccountType(val type: String) {
    CONSUMER("consumer"),
    MERCHANT("merchant"),
    IN_PLAYER("inplayer")
}