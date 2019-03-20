package com.sdk.inplayer.model.account

/**
 * Created by victor on 3/19/19
 */

data class InPlayerRegisterFields(
        val default_value: String,
        val id: Int,
        val label: String,
        val name: String,
        val placeholder: String,
        val required: Boolean,
        val type: RegisterFieldType
)


sealed class RegisterFieldType {
    
    class Dropdown(val options: Map<String, String>) : RegisterFieldType()
    
    class Radio(val options: Map<String, String>) : RegisterFieldType()
    
    class Country(val options: List<InPlayerFieldCountry>) : RegisterFieldType()
    
    class Input : RegisterFieldType()
    
    class Datepicker : RegisterFieldType()
    
    class Checkbox : RegisterFieldType()
    
    class Unkown : RegisterFieldType()
    
}

