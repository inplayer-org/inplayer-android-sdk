package com.s.domain.entity

/**
 * Created by victor on 12/20/18
 */
data class InPlayerDomainUser(val id: Long, val email: String, val fullName: String,
                              val referrer: String, val isCompleted: Boolean,
                              val createdAt: Long, val updatedAt: Long, val roles: List<String>)