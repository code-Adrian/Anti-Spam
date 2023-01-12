package com.ab.anti_spam.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ab.anti_spam.firebase.FirebaseAuthManager
import com.google.firebase.auth.FirebaseUser

class LoggedInViewModel(app: Application) : AndroidViewModel(app) {

    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app,null)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser
    var loggedOut : MutableLiveData<Boolean> = firebaseAuthManager.loggedOut
    var userId = firebaseAuthManager.firebaseAuth!!.currentUser!!.uid

    fun logOut() {
        firebaseAuthManager.logOut()
    }
}