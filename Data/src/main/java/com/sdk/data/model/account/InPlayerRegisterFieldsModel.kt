package com.sdk.data.model.account

import com.google.gson.annotations.SerializedName

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
        val type: RegisterFieldType,
        val options: Any?
)

enum class RegisterFieldType {
    @SerializedName("select")
    Dropdown,
    @SerializedName("radio")
    Radio,
    @SerializedName("country")
    Country,
    @SerializedName("input")
    Input,
    @SerializedName("datepicker")
    Datepicker,
    @SerializedName("checkbox")
    Checkbox
}




