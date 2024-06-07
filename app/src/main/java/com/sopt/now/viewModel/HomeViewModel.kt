package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.ServicePool
import com.sopt.now.data.Friend
import com.sopt.now.data.MyProfile
import com.sopt.now.data.model.ResponseInfoDto
import com.sopt.now.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    val authRepository: AuthRepository = AuthRepository(ServicePool.authService)


    private val _myProfile = MutableStateFlow<List<MyProfile>>(emptyList())
    val myProfile: StateFlow<List<MyProfile>> = _myProfile

    private val _friendList = MutableStateFlow<List<Friend>>(emptyList())
    val friendList: StateFlow<List<Friend>> = _friendList

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState get() = _homeState.asStateFlow()

    fun searchInfo(memberId: Int) {
        _homeState.value = HomeState.Loading
        viewModelScope.launch {
            runCatching {
                authRepository.getInfo(memberId)
            }.onSuccess { responseInfoDto ->
                _homeState.value = HomeState.Success(responseInfoDto = responseInfoDto)
            }.onFailure {
                _homeState.value = HomeState.Error("Error")

            }
        }
    }

    init {
        _myProfile.value = listOf(
            MyProfile(
                profileImage = R.drawable.baseline_person_outline_24,
                name = "",
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

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val responseInfoDto: ResponseInfoDto) : HomeState()
    data class Error(val message: String) : HomeState()
}

