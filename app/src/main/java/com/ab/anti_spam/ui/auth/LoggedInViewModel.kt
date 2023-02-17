package com.ab.anti_spam.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ab.anti_spam.firebase.FirebaseAuthManager
import com.ab.anti_spam.localstorage.SettingsStorage
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.SettingsModel
import com.google.firebase.auth.FirebaseUser

class LoggedInViewModel(app: Application) : AndroidViewModel(app) {

    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app,null)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser
    var loggedOut : MutableLiveData<Boolean> = firebaseAuthManager.loggedOut

    fun loadSettings(app: Main){
        val settings = SettingsStorage(app.applicationContext)
        val settingsModel = liveFirebaseUser.value?.let { SettingsModel(uid = it.uid, scan_sms = true, database_block = true, personal_block = true, local_store_block = true, unknown_block = true, community_block_num = 3, theme = true) }
        if (settingsModel != null) {
            settings.initialize(settingsModel)
        }
    }

    fun logOut() {
        firebaseAuthManager.logOut()
    }
}