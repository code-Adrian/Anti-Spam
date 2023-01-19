package com.ab.anti_spam.localstorage

import com.ab.anti_spam.models.SMSBlacklistModel

interface SMSBlacklistInterface {
    fun getAll() : MutableList<SMSBlacklistModel>
    fun serialize(model: MutableList<SMSBlacklistModel>)
    fun deserialize()
    fun update(model: SMSBlacklistModel)
    fun add(model: SMSBlacklistModel)
    fun delete(model: SMSBlacklistModel)
}