package com.sopt.now.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Text(text = "메인 화면")
}

//@Preview(showBackground = true)
@Composable
fun SignupScreen() {
    NOWSOPTAndroidTheme {
        var textid by remember { mutableStateOf("") }
        var textpw by remember { mutableStateOf("") }
        var textnn by remember { mutableStateOf("") }
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
                singleLine = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textnn,
                onValueChange = { textpw = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("닉네임을 입력해주세요") },
                placeholder = { Text("") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(120.dp))
            val context = LocalContext.current
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (textid.length + textpw.length + textnn.length > 0) {
                        Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                        SignupScreen()
                    } else {
                        Toast.makeText(context, "회원가입이 불가능 합니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    NOWSOPTAndroidTheme {
        var textid by remember { mutableStateOf("") }
        var textpw by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxWidth(),
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
            )
            TextField(
                value = textpw,
                onValueChange = { textpw = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("비밀번호 입력") },
                placeholder = { Text("") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(120.dp))
            val context = LocalContext.current
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (textid == "") { //Todo: 로그인 되는 조건문 구현
                    } else {
                        Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        MainScreen()
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "로그인 하기")
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = { SignupScreen() },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}