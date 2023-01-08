package com.ab.anti_spam.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ab.anti_spam.databinding.LoginBinding
import androidx.lifecycle.Observer
import pub.devrel.easypermissions.EasyPermissions

//

class Login: AppCompatActivity() {

    private lateinit var loginViewModel : LoginViewModel
    private lateinit var loginBinding: LoginBinding
    private lateinit var loggedInViewModel : LoggedInViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash screen here

        loginBinding = LoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
    }




    public override fun onStart() {
        super.onStart()
        loginViewModel = ViewModelProvider(this,viewModelFactory { LoginViewModel(this.application,this) }).get(LoginViewModel::class.java)
        loggedInViewModel = ViewModelProvider(this).get(LoggedInViewModel::class.java)
        loginListener()

    }



    private fun loginListener(){
        loginBinding.welcomeButton.setOnClickListener{
          //  if(hasLocationPermission()) {
                loginViewModel.login()
          //  }else{
             //   requestLocationPermission()
           // }
        }
    }
    override fun onStop() {
        super.onStop()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
        }
}