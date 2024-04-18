package com.sopt.now.compose


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun LoginScreen(navController: NavHostController, userID: String?, userPasswd: String?, userNickname: String?, userMBTI: String?) {

    var textid by remember { mutableStateOf("") }
    var textpw by remember { mutableStateOf("") }
    val context = LocalContext.current




    NOWSOPTAndroidTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Welcome to SOPT")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textid,
                onValueChange = { textid = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("사용자 이름 입력") },
                placeholder = { Text("") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                    }
                )
            )
            TextField(
                value = textpw,
                onValueChange = { textpw = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("비밀번호 입력") },
                placeholder = { Text("") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(120.dp))

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (textid == userID && textpw == userPasswd && textid.isNotEmpty() && textpw.isNotEmpty()) {
                        Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        Log.d("LoginScreen","Login Success")
                        navController.navigate(Routes.MyPage.route+"/$textid/$textpw/$userNickname/$userMBTI")
                    } else {
                        Toast.makeText(context, "로그인 실패! 입력 ID: $textid 회원가입 ID: $userID", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "로그인 하기")
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = { navController.navigate(Routes.SignUp.route)
                }
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}
