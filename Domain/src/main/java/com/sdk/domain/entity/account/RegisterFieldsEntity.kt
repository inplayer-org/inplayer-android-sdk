package com.sdk.domain.entity.account

data class RegisterFieldsEntity(
        val default_value: String,
        val id: Int,
        val label: String,
        val name: String,
        val placeholder: String,
        val required: Boolean,
        val type: RegisterFieldTypeEntity,
        val options: Any?
)

enum class RegisterFieldTypeEntity(val value: String) {
    Dropdown("select"),
    Radio("radio"),
    Country("country"),
    Input("input"),
    Datepicker("datepicker"),
    Checkbox("checkbox")
}

