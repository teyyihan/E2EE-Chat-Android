package com.teyyihan.core.base


import androidx.appcompat.app.AppCompatActivity
import com.teyyihan.core.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

}