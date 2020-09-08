package com.teyyihan.e2ee_chat.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.data.util.Resource
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentLoginBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : BaseFragment() {

    private val TAG = "teooo LoginFragment"
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.signupFragmentSignupButton.setOnClickListener {

            if(isPasswordOrUsernameEmpty()){
                toast("Username or password is empty")
            }else{
                lifecycleScope.launchWhenStarted {
                    viewModel.token(binding.loginFragmentUsernameEditText.text.toString(),
                        binding.loginFragmentPasswordEditText.text.toString()).collectLatest {
                        when(it){
                            is Resource.Success -> {
                                toast(it.value.toString())
                            }
                            is Resource.GenericError -> {
                                toast(it.errorMessage)
                            }
                        }
                    }
                }

            }
        }

        return binding.root
    }

    private fun isPasswordOrUsernameEmpty(): Boolean {
        return binding.loginFragmentUsernameEditText.text.isNullOrEmpty()
                || binding.loginFragmentPasswordEditText.text.isNullOrEmpty()
    }


}