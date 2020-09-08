package com.teyyihan.core.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment: Fragment() {

    fun toast(text: String?){
        Toast.makeText(requireContext(),text,Toast.LENGTH_LONG).show()
    }

}