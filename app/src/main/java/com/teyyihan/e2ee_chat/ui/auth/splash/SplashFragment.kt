package com.teyyihan.e2ee_chat.ui.auth.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.e2ee_chat.databinding.FragmentSplashBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.Integer.toHexString
import java.security.AlgorithmParameters
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PublicKey
import java.security.interfaces.ECPublicKey
import java.security.spec.ECParameterSpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.crypto.ShortBufferException
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey
import javax.crypto.spec.DHParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject


class SplashFragment : BaseFragment() {

    private val TAG = "teooo SplashFragment"

    @Inject
    lateinit var sessionManager: SessionManager
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)


//        /***
//         *  [by viewModels] acts lazy, this means in order to call init{} block
//         *  we need to call any function on ViewModel
//         */
//        viewModel.init()

        return binding.root
    }

}

