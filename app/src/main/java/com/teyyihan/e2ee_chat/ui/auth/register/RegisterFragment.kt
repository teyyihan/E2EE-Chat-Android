package com.teyyihan.e2ee_chat.ui.auth.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment() {

    private val TAG = "teooo RegisterFragment"
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)

        binding.signupFragmentSignupButton.setOnClickListener {
            Log.d(TAG, "onCreateView: button clicked")
            viewModel.registerFlow(
                binding.loginFragmentUsernameEditText.text.toString(),
                binding.loginFragmentPasswordEditText.text.toString()
            )
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}