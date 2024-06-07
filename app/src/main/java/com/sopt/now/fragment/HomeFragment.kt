package com.sopt.now.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.adapter.FriendAdapter
import com.sopt.now.adapter.MyProfileAdapter
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.viewModel.HomeViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "바인딩 객체 생성 완료" }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myProfileAdapter = MyProfileAdapter()
        val friendAdapter = FriendAdapter()
        val concatAdapter = ConcatAdapter(myProfileAdapter, friendAdapter)

        binding.rvFriends.run {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.myProfile.flowWithLifecycle(lifecycle).onEach {
            myProfileAdapter.setMyProfile(it)
        }.launchIn(lifecycleScope)

        viewModel.friendList.flowWithLifecycle(lifecycle).onEach {
            friendAdapter.setFriendList(it)
        }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}