package com.sopt.now.compose

import android.app.Activity
import android.content.Context
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                SignupScreen()
            }
        }
    }
}


@Composable
fun SignupScreen() {
    var textid by remember { mutableStateOf("") }
    var textpw by remember { mutableStateOf("") }
    var textnn by remember { mutableStateOf("") }
    var textmbti by remember { mutableStateOf("") }
    val context = LocalContext.current


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
            onClick = {
                if (textid.length in 6..10) {
                    if (textpw.length in 8..12) {
                        if (textnn.trim().isNotEmpty()) {
                            if (isValidMBTIFormat(textmbti)) {
                                // All fields are valid, proceed to registration
                                Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, LoginActivity::class.java)
                                intent.putExtra("ID", textid)
                                intent.putExtra("PW", textpw)
                                intent.putExtra("NN", textnn)
                                intent.putExtra("MBTI", textmbti)
                                (context as Activity).setResult(Activity.RESULT_OK,intent)
                                context.finish()

                            } else {
                                Toast.makeText(context, "MBTI의 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "닉네임은 한 글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "비밀번호는 8~12글자여야 합니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "아이디는 6~10글자여야 합니다.", Toast.LENGTH_SHORT).show()
                }

            }
        ) {
            Text(text = "회원가입 하기")
        }
    }
}

private fun isValidMBTIFormat(mbti: String): Boolean {
    val validMBTIRegex = Regex("[EI][NS][FT][JP]")
    return mbti.uppercase().matches(validMBTIRegex)
}

