package com.ab.anti_spam.localstorage

import com.ab.anti_spam.models.LocalBlockModel

interface LocalBlockInterface {

    fun serialize(model: MutableList<LocalBlockModel>)
    fun deserialize()
    fun getAll() : MutableList<LocalBlockModel>
    fun checkIfExists() : Boolean
}