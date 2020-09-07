package com.teyyihan.e2ee_chat.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teyyihan.core.base.BaseActivity
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.data.model.UserLocal
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment() {

    private val TAG = "teooo SplashFragment"

    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSplashBinding.inflate(inflater,container,false)

        (activity as BaseActivity).sessionManager.cachedUser.observe(viewLifecycleOwner){
            sessionCheck(it)
        }

        return binding.root
    }

    private fun sessionCheck(userLocal: UserLocal?) {
        if(userLocal == null || userLocal.isRefreshTokenExpired()){
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }else{
            //TODO: Refresh access token, if failed, go to login fragment
            //findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }


}