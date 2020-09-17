package com.teyyihan.e2ee_chat.ui.app.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentMainBinding


class MainFragment : BaseFragment() {

    private val TAG = "teooo MainFragment"
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var _adapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        _adapter = MainListAdapter().apply {
            friendListClickListener = characterListener
        }

        binding.mainFragmentList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = _adapter
        }

        viewModel.getFriend().observe(viewLifecycleOwner){
            Log.d(TAG, "onCreateView: friends ${it.size}")
            _adapter.submitList(it)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
//            viewModel.insertFriend()
        }

        return binding.root
    }


    private val characterListener = object : FriendListClickListener{
        override fun onFriendClicked(friend: Friend) {
            Log.d(TAG, "onFriendClicked: friend clicked ${friend.friendUsername}")
            val action = MainFragmentDirections.actionMainFragmentToChatFragment(friend)
            findNavController().navigate(action)
        }
    }

}