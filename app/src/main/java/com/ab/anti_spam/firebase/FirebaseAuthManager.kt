package com.ab.anti_spam.firebase

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.ab.anti_spam.R
import com.ab.anti_spam.activities.Home
import com.ab.anti_spam.ui.auth.Login
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

class FirebaseAuthManager(application: Application, login: Login?) {

    val AUTH_CODE = 400
    lateinit var listener: FirebaseAuth.AuthStateListener
    lateinit var providers: List<AuthUI.IdpConfig>

    private var application: Application? = null
    private var loginInstance: Login? = null

    var firebaseAuth: FirebaseAuth? = null
    var firebaseUI: AuthUI? = null
    var liveFirebaseUser = MutableLiveData<FirebaseUser>()
    var loggedOut = MutableLiveData<Boolean>()

    init {
        providers()
        this.application = application
        this.loginInstance = login
        firebaseUI = AuthUI.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth!!.currentUser != null) {
            liveFirebaseUser.postValue(firebaseAuth!!.currentUser)
            loggedOut.postValue(false)
        }
    }

    fun providers(){
        providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
        )
    }

    fun login(){

        listener = object: FirebaseAuth.AuthStateListener{
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                val user = p0.currentUser
                if(user != null){ //If authenticated
                    loginInstance?.startActivity(Intent(application, Home::class.java))
                }else{ //Not authenticated
                    try {
                        loginInstance?.startActivityForResult(
                            AuthUI.getInstance().createSignInIntentBuilder().setTheme(R.style.UItheme).setLogo(R.drawable.textlesslogo)
                                .setAvailableProviders(providers).build(), AUTH_CODE)
                    }catch (e: Exception){
                        println("Error occurred " +e.toString())
                    }
                }
            }
        }
        firebaseAuth?.addAuthStateListener(listener)
    }

    fun logOut() {
        firebaseAuth!!.signOut()
        firebaseUI!!.signOut(application!!.applicationContext)
        loggedOut.postValue(true)
    }

}