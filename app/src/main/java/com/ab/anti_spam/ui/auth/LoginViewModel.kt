package com.ab.anti_spam.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ab.anti_spam.firebase.FirebaseAuthManager
import com.google.firebase.auth.FirebaseUser

class LoginViewModel (app: Application, login: Login) : AndroidViewModel(app)  {
    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app,login)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser

    fun login(){
        firebaseAuthManager.login()
    }
}