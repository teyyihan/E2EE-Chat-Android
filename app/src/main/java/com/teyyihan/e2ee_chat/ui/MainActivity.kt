package com.teyyihan.e2ee_chat.ui

import android.os.Bundle
import com.teyyihan.core.base.BaseActivity
import com.teyyihan.e2ee_chat.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val TAG = "teooo MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logout listener
        sessionManager.cachedUser.observe(this) { userLocal ->
            if(userLocal == null || userLocal.isRefreshTokenExpired()){
                //TODO: Logout and navigate to LoginFragment
            }
        }

    }

}
