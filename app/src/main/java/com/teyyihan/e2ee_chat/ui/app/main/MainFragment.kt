package com.teyyihan.e2ee_chat.ui.app.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.teyyihan.core.base.BaseActivity
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.core.util.AuthState
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentMainBinding


class MainFragment : BaseFragment() {

    private val TAG = "teooo MainFragment"
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        viewModel.getFriend()

        return binding.root
    }

}