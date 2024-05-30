import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.RequestPasswordDto
import com.sopt.now.compose.data.ResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _changePasswordState =
        MutableStateFlow<ChangePasswordState>(ChangePasswordState.Idle)
    val changePasswordState: StateFlow<ChangePasswordState> = _changePasswordState

    fun changePassword(
        memberId: Int,
        previousPassword: String,
        newPassword: String,
        newPasswordVerification: String
    ) {
        _changePasswordState.value = ChangePasswordState.Loading

        val passwordRequest =
            RequestPasswordDto(previousPassword, newPassword, newPasswordVerification)
        authService.changePassword(memberId, passwordRequest)
            .enqueue(object : Callback<ResponseDto> {
                override fun onResponse(call: Call<ResponseDto>, response: Response<ResponseDto>) {
                    if (response.isSuccessful) {
                        _changePasswordState.value = ChangePasswordState.Success
                    } else {
                        _changePasswordState.value = ChangePasswordState.Error("비밀번호 변경 실패")
                        Log.d("error", "실패1")
                    }
                }

                override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                    _changePasswordState.value =
                        ChangePasswordState.Error("비밀번호 변경 요청 실패: ${t.message}")
                    Log.d("error", "실패2")
                }
            })
    }

    sealed class ChangePasswordState {
        object Idle : ChangePasswordState()
        object Loading : ChangePasswordState()
        object Success : ChangePasswordState()
        data class Error(val message: String) : ChangePasswordState()
    }
}
