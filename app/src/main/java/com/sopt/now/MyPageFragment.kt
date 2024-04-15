package com.sopt.now
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentMypageBinding

class MyPageFragment: Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) {
            "바인딩 객체 생성 완료"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val friendAdapter = FriendAdapter()

        binding.tvmypage.run {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "신민석",
            selfDescription = "잘 이동하는건가?",
        )
    )


}