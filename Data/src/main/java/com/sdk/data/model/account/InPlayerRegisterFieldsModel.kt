package com.sdk.data.model.account

/**
 * Created by victor on 3/13/19
 */
data class InPlayerRegisterFieldsModel(
        val default_value: String,
        val id: Int,
        val label: String,
        val name: String,
        val placeholder: String,
        val required: Boolean,
        val type: String,
        val options: HashMap<String, String>
)