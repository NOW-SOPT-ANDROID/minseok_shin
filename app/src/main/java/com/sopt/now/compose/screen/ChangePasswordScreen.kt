package com.sopt.now.compose.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.LocalNavGraphViewModelStoreOwner
import com.sopt.now.compose.NavViewModel
import com.sopt.now.compose.Routes
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.RequestPasswordDto
import com.sopt.now.compose.data.ResponsePasswordDto
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ChangePasswordScreen(navController: NavHostController) {

    var previousPassword by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var newPasswordCheck by remember {
        mutableStateOf("")
    }

    val navViewModel: NavViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    val authService by lazy { ServicePool.authService }

    val context = LocalContext.current

    fun getPasswordRequestDto(): RequestPasswordDto {
        val previousPw = previousPassword
        val newPw = newPassword
        val newPwCheck = newPasswordCheck

        return RequestPasswordDto(
            previousPassword = previousPw,
            newPassword = newPw,
            newPasswordVerification = newPwCheck,

            )
    }

    fun changePassword(memberId: Int) {
        val passwordRequest = getPasswordRequestDto()
        authService.changePassword(memberId, passwordRequest)
            .enqueue(object : Callback<ResponsePasswordDto> {
                override fun onResponse(
                    call: Call<ResponsePasswordDto>,
                    response: Response<ResponsePasswordDto>,
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            context,
                            "비밀번호 변경 완료! 새 비밀번호로 로그인 하세요",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Password.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    } else {
                        Toast.makeText(context, "비밀번호 변경 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePasswordDto>, t: Throwable) {
                    Toast.makeText(context, "비밀번호 변경 요청 실패: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }


    NOWSOPTAndroidTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Welcome to SOPT")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = previousPassword,
                onValueChange = { previousPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("기존 비밀번호 입력") },
                placeholder = { Text("") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                    }
                )
            )
            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("새 비밀번호 입력") },
                placeholder = { Text("") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            TextField(
                value = newPasswordCheck,
                onValueChange = { newPasswordCheck = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("새 비밀번호 확인") },
                placeholder = { Text("") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    changePassword(navViewModel.memberId)
                }
            ) {
                Text(text = "비밀번호 변경")
            }

        }
    }
}