package com.ab.anti_spam.main

import android.app.Application
import com.ab.anti_spam.localstorage.CallBlacklistStorage
import com.ab.anti_spam.localstorage.LocalBlockStorage
import com.ab.anti_spam.localstorage.SMSBlacklistStorage
import com.ab.anti_spam.localstorage.SettingsStorage
import com.ab.anti_spam.models.CallBlacklistModel
import com.ab.anti_spam.models.LocalBlockModel

class Main : Application() {
lateinit var localCallBlacklist : CallBlacklistStorage
lateinit var localSMSBlacklist : SMSBlacklistStorage
lateinit var localBlockBlacklist : LocalBlockStorage
lateinit var settings : SettingsStorage
    override fun onCreate() {
        super.onCreate()
        localCallBlacklist = CallBlacklistStorage(applicationContext)
        localSMSBlacklist = SMSBlacklistStorage(applicationContext)
        localBlockBlacklist = LocalBlockStorage(applicationContext)
        settings = SettingsStorage(applicationContext)
    }
}