package com.sdk.domain.entity.account

data class RegisterFieldsEntity(
        val default_value: String,
        val id: Int,
        val label: String,
        val name: String,
        val placeholder: String,
        val required: Boolean,
        val type: String,
        val options: HashMap<String, String>
)