package com.sopt.now.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                LoginScreen()
            }
        }
    }
}


@Composable
fun LoginScreen() {
    var textid by remember { mutableStateOf("") }
    var textpw by remember { mutableStateOf("") }
    val context = LocalContext.current

    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }
    var userNn by remember { mutableStateOf("") }
    var userMBTI by remember { mutableStateOf("") }

    val nameId = "ID"
    val namePassword = "PASSWORD"
    val nameNickname = "NICKNAME"
    val nameMbti = "MBTI"


    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            userId = data?.getStringExtra(nameId) ?: ""
            userPw = data?.getStringExtra(namePassword) ?: ""
            userNn = data?.getStringExtra(nameNickname) ?: ""
            userMBTI = data?.getStringExtra(nameMbti) ?: ""
        } else {
            Toast.makeText(context, "데이터 못 받아옴", Toast.LENGTH_SHORT).show()
        }
    }


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
                if (textid == userId && textpw == userPw && textid.isNotEmpty() && textpw.isNotEmpty()) {
                    Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra(nameId, textid)
                    intent.putExtra(namePassword, textpw)
                    intent.putExtra(nameNickname, userNn)
                    intent.putExtra(nameMbti, userMBTI)
                    (context as Activity).setResult(Activity.RESULT_OK,intent)
                    context.startActivity(intent)
                    context.finish()
                } else {
                    Toast.makeText(context, "로그인 실패!", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "로그인 하기")
        }
        Button(
            modifier = Modifier.padding(10.dp),
            onClick = { launcher.launch(Intent(context, SignupActivity::class.java))
            }
        ) {
            Text(text = "회원가입 하기")
        }
    }
}
