package com.sopt.now.compose.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sopt.now.compose.Routes
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.RequestSignUpDto
import com.sopt.now.compose.data.ResponseSignUpDto
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun SignupScreen(navController: NavController) {
    var textId by remember { mutableStateOf("") }
    var textPw by remember { mutableStateOf("") }
    var textNickname by remember { mutableStateOf("") }
    var textPhone by remember { mutableStateOf("") }
    val context = LocalContext.current


    val authService by lazy { ServicePool.authService }

    fun getSignUpRequestDto(): RequestSignUpDto {
        val id = textId
        val password = textPw
        val nickname = textNickname
        val phoneNumber = textPhone
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }


    fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        context,
                        "회원가입 성공 유저의 ID는 $userId 입니둥",
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d("SignUp", "data: $data, userId: $userId")
                    navController.navigate(Routes.Login.route)
                } else {
                    val errorC = response.code()
                    val errorM = response.message()
                    Toast.makeText(
                        context,
                        "회원가입 실패 $errorC , $errorM",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Toast.makeText(context, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
        })
    }




    NOWSOPTAndroidTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textId,
                onValueChange = { textId = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("아이디를 입력해주세요.") },
                placeholder = { Text("") },
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textPw,
                onValueChange = { textPw = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("비밀번호를 입력해주세요") },
                placeholder = { Text("") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textNickname,
                onValueChange = { textNickname = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("닉네임을 입력해주세요") },
                placeholder = { Text("") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textPhone,
                onValueChange = { textPhone = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("전화번호를 입력해주세요") },
                placeholder = { Text("") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(120.dp))
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    signUp()
                }
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}

//private fun isValidMBTIFormat(mbti: String): Boolean {
//    val validMBTIRegex = Regex("[EI][NS][FT][JP]")
//    return mbti.uppercase().matches(validMBTIRegex)
//}

