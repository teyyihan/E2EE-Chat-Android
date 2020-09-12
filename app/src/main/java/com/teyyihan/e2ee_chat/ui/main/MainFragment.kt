package com.teyyihan.e2ee_chat.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teyyihan.core.base.BaseActivity
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.core.util.AuthState
import com.teyyihan.e2ee_chat.R


class MainFragment : BaseFragment() {

    private val TAG = "teooo MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}