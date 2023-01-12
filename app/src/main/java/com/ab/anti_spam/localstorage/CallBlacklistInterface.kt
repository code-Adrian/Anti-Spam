package com.ab.anti_spam.localstorage

import com.ab.anti_spam.models.CallBlacklistModel

interface CallBlacklistInterface {
    fun getCurrentUserModel(uid: String) : CallBlacklistModel
    fun serialize(model: CallBlacklistModel)
    fun deserialize()
    fun update(model: CallBlacklistModel,uid: String)
}