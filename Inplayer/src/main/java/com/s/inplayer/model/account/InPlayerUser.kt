package com.s.inplayer.model.account

/**
 * Created by victor on 1/4/19
 */
data class InPlayerUser(val id: Long,
                        val email: String,
                        val fullName: String,
                        val referrer: String,
                        val isCompleted: Boolean,
                        val createdAt: Long,
                        val updatedAt: Long,
                        val roles: List<String>,
                        val merchantId: String,
                        val merchantUUID: String,
                        val metadata: HashMap<String, String>,
                        val uuid: String,
                        val username: String)