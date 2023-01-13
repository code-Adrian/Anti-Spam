package com.ab.anti_spam.localstorage

import com.ab.anti_spam.models.CallBlacklistModel

interface CallBlacklistInterface {
    fun getAll() : MutableList<CallBlacklistModel>
    fun serialize(model: MutableList<CallBlacklistModel>)
    fun deserialize()
    fun update(model: CallBlacklistModel)
    fun add(model: CallBlacklistModel)
    fun delete(model: CallBlacklistModel)
}