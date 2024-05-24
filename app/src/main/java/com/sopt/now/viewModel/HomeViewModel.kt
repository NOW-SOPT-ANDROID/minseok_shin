package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.R
import com.sopt.now.dataClass.Friend
import com.sopt.now.dataClass.MyProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(userNickname: String) : ViewModel() {
    private val _myProfile = MutableStateFlow<List<MyProfile>>(emptyList())
    val myProfile: StateFlow<List<MyProfile>> = _myProfile

    private val _friendList = MutableStateFlow<List<Friend>>(emptyList())
    val friendList: StateFlow<List<Friend>> = _friendList

    init {
        _myProfile.value = listOf(
            MyProfile(
                profileImage = R.drawable.baseline_person_outline_24,
                name = userNickname,
                selfDescription = "내 프로필 소개 텍스트"
            )
        )

        val friendIcon = R.drawable.baseline_person_24
        _friendList.value = listOf(
            Friend(
                profileImage = friendIcon,
                name = "강문수",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "공세영",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "김명석",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "김아린",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "김언지",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "김윤서",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "박동민",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "박유진",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "배지현",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "배찬우",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "손민재",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "송혜음",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "우상욱",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "유정현",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "윤서희",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "곽의진",
                selfDescription = "34기 안드로이드 파트장",
            ), Friend(
                profileImage = friendIcon,
                name = "이가을",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "이나경",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "이석준",
                selfDescription = "34기 안드로이드 YB",
            ),
            Friend(
                profileImage = friendIcon,
                name = "이석찬",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "이연진",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "이유빈",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "임하늘",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "주효은",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "최준서",
                selfDescription = "34기 안드로이드 OB",
            ), Friend(
                profileImage = friendIcon,
                name = "이현진",
                selfDescription = "34기 안드로이드 YB",
            ), Friend(
                profileImage = friendIcon,
                name = "박효빈",
                selfDescription = "34기 안드로이드 YB",
            )

        )
    }
}

class HomeViewModelFactory(private val userNickname: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userNickname) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



