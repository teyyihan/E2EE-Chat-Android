package com.teyyihan.e2ee_chat.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.teyyihan.core.base.BaseActivity
import com.teyyihan.domain.friend.util.AuthState
import com.teyyihan.domain.friend.util.AuthStep
import com.teyyihan.data.model.UserLocal
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val TAG = "teooo MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        sessionManager.authState.observe(this){
            when(it){
                is AuthState.Success -> {
                    handleSuccessAuthState(it)
                }
                is AuthState.Loading -> {
                    handleLoadingAuthState(it)
                }
                is AuthState.Error -> {
                    handleErrorAuthState(it)
                }
            }
        }

    }

    private fun handleErrorAuthState(it: AuthState.Error) {
        it.getValueOnce()?.let {
            when(it.where){

                AuthStep.CACHE -> {
                    Log.d(TAG, "handleErrorAuthState: ERROR ON SPLASH")
                    navController.navigate(R.id.action_splashFragment_to_loginFragment)
                }

                AuthStep.LOGIN -> {
                    Log.d(TAG, "handleErrorAuthState: ERROR ON LOGIN ${it.errorMessage} ${it.exception?.localizedMessage} ${it.where}")
                }

                AuthStep.REGISTER -> {
                    Log.d(TAG, "handleErrorAuthState: ERROR ON REGISTER ${it.errorMessage}")
                }
            }
        }
    }

    private fun handleLoadingAuthState(it: AuthState.Loading) {
        Log.d(TAG, "handleLoadingAuthState: loading somewhere")
        Log.d(TAG, "handleLoadingAuthState: ${sessionManager.authState.value}")
    }

    private fun handleSuccessAuthState(it: AuthState.Success<UserLocal>) {
        it.getValueOnce()?.let {
            Log.d(TAG, "handleSuccessAuthState: LOGGED IN")
            navController.navigate(R.id.action_global_mainFragment)
        }
    }

}

fun <T> AuthState.Success<T>.getValueOnce() = this.value.getContentIfNotHandled()
fun AuthState.Error.getValueOnce() = this.errorModel.getContentIfNotHandled()
