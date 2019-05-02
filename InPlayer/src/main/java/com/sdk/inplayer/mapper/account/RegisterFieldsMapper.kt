package com.sdk.inplayer.mapper.account

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sdk.domain.entity.account.RegisterFieldTypeEntity
import com.sdk.domain.entity.account.RegisterFieldsEntity
import com.sdk.domain.entity.mapper.DomainMapper
import com.sdk.inplayer.R
import com.sdk.inplayer.model.account.InPlayerFieldCountry
import com.sdk.inplayer.model.account.InPlayerRegisterFields
import com.sdk.inplayer.model.account.RegisterFieldType

/**
 * Created by victor on 3/19/19
 */
internal class RegisterFieldsMapper constructor(val context: Context) : DomainMapper<RegisterFieldsEntity, InPlayerRegisterFields> {
    
    override fun mapFromDomain(model: RegisterFieldsEntity): InPlayerRegisterFields {
        
        
        when (model.type) {
            RegisterFieldTypeEntity.Country -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Country(parseCountriesFromRawJson()),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
            RegisterFieldTypeEntity.Dropdown -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Dropdown(model.options as Map<String, String>),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
            RegisterFieldTypeEntity.Radio -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Radio(model.options as Map<String, String>),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
            RegisterFieldTypeEntity.Checkbox -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Checkbox(),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
            RegisterFieldTypeEntity.Datepicker -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Datepicker(),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
            
            RegisterFieldTypeEntity.Input -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Input(),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
            
            else -> {
                return InPlayerRegisterFields(default_value = model.default_value,
                        type = RegisterFieldType.Unkown(),
                        label = model.label,
                        required = model.required,
                        id = model.id,
                        name = model.name,
                        placeholder = model.placeholder
                )
            }
        }
        
    }
    
    override fun mapToDomain(viewModel: InPlayerRegisterFields): RegisterFieldsEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    
    private fun parseCountriesFromRawJson(): List<InPlayerFieldCountry> {
        val text = context.resources.openRawResource(R.raw.countries)
                .bufferedReader().use { it.readText() }
        
        
        val listType = object : TypeToken<List<InPlayerFieldCountry>>() {}.type
        return Gson().fromJson<List<InPlayerFieldCountry>>(text, listType)
    }
    
}