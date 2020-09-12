package com.teyyihan.e2ee_chat.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.teyyihan.core.base.BaseActivity
import com.teyyihan.core.util.AuthState
import com.teyyihan.core.util.AuthStep
import com.teyyihan.data.model.UserLocal
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : BaseActivity() {

    private val TAG = "teooo MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

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
                    Log.d(TAG, "handleErrorAuthState: ERROR ON SPLASH 1111111111111111")
                    navController.navigate(R.id.action_splashFragment_to_loginFragment)
                }

                AuthStep.LOGIN -> {
                    Log.d(TAG, "handleErrorAuthState: ERROR ON LOGIN 111111111111111111")
                }

            }
        }
    }

    private fun handleLoadingAuthState(it: AuthState.Loading) {
        Log.d(TAG, "handleLoadingAuthState: loading somewhere")
    }

    private fun handleSuccessAuthState(it: AuthState.Success<UserLocal>) {
        it.getValueOnce()?.let {
            Log.d(TAG, "handleSuccessAuthState: SUCCESSFULLY LOGGED IN 111111111111111111")
            navController.navigate(R.id.action_global_mainFragment)
        }
    }

}

fun <T> AuthState.Success<T>.getValueOnce() = this.value.getContentIfNotHandled()
fun AuthState.Error.getValueOnce() = this.errorModel.getContentIfNotHandled()
