package com.sopt.now.compose

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


@Composable
fun SignupScreen(navController: NavController) {
    var textId by remember { mutableStateOf("") }
    var textPw by remember { mutableStateOf("") }
    var textNickname by remember { mutableStateOf("") }
    var textPhone by remember { mutableStateOf("") }
    val context = LocalContext.current

    val navViewModel: NavViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)



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

                }
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}

private fun isValidMBTIFormat(mbti: String): Boolean {
    val validMBTIRegex = Regex("[EI][NS][FT][JP]")
    return mbti.uppercase().matches(validMBTIRegex)
}

