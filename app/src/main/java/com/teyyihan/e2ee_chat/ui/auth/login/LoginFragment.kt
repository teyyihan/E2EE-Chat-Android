package com.teyyihan.e2ee_chat.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment() {

    private val TAG = "teooo LoginFragment"
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.signupFragmentSignupButton.setOnClickListener {
            viewModel.loginAttempt(
                binding.loginFragmentUsernameEditText.text.toString(),
                binding.loginFragmentPasswordEditText.text.toString()
            )
        }

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }


    private fun fieldsEmpty(): Boolean = binding.loginFragmentUsernameEditText.text.isNullOrBlank()
            || binding.loginFragmentPasswordEditText.text.isNullOrEmpty()


}