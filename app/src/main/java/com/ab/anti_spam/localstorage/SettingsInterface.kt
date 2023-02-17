package com.ab.anti_spam.localstorage

import com.ab.anti_spam.models.SettingsModel

interface SettingsInterface {

    fun getAll() : MutableList<SettingsModel>
    fun initialize(model: SettingsModel)
    fun serialize(model: MutableList<SettingsModel>)
    fun update(model: SettingsModel)
    fun deserialize()
}