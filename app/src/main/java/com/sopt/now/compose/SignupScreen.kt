package com.sopt.now.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


@Composable
fun SignupScreen(navController: NavController) {
    var textid by remember { mutableStateOf("") }
    var textpw by remember { mutableStateOf("") }
    var textnn by remember { mutableStateOf("") }
    var textmbti by remember { mutableStateOf("") }
    val context = LocalContext.current


    NOWSOPTAndroidTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textid,
                onValueChange = { textid = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("아이디를 입력해주세요.") },
                placeholder = { Text("") },
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textpw,
                onValueChange = { textpw = it },
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
                value = textnn,
                onValueChange = { textnn = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("닉네임을 입력해주세요") },
                placeholder = { Text("") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textmbti,
                onValueChange = { textmbti = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("MBTI를 입력해주세요") },
                placeholder = { Text("") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(120.dp))
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {when {
                    textid.length !in 6..10 -> {
                        Toast.makeText(context, "아이디는 6~10글자여야 합니다.", Toast.LENGTH_SHORT).show()
                    }
                    textpw.length !in 8..12 -> {
                        Toast.makeText(context, "비밀번호는 8~12글자여야 합니다.", Toast.LENGTH_SHORT).show()
                    }
                    textnn.trim().isEmpty() -> {
                        Toast.makeText(context, "닉네임은 한 글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                    }
                    !isValidMBTIFormat(textmbti) -> {
                        Toast.makeText(context, "MBTI의 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.d("SignUp","SignUp Success")
                        Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                        navController.navigate("${Routes.Login.route}?userID=$textid&userPasswd=$textpw&userNickname=$textnn&userMBTI=$textmbti")
                        Log.d("SignUp","Go LoginScreen")
                    }
                }
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

