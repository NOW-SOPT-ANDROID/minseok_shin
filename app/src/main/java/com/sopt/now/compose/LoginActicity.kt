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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    // State variables to hold user information
    var textid by remember { mutableStateOf("") }
    var textpw by remember { mutableStateOf("") }
    val context = LocalContext.current

    // State variables to hold user information received from SignupActivity
    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }
    var userNn by remember { mutableStateOf("") }
    var userMBTI by remember { mutableStateOf("") }




    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        Toast.makeText(context, "실행", Toast.LENGTH_LONG).show()
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            // Retrieve user information from the result
            userId = data?.getStringExtra("ID") ?: ""
            userPw = data?.getStringExtra("PW") ?: ""
            userNn = data?.getStringExtra("NN") ?: ""
            userMBTI = data?.getStringExtra("MBTI") ?: ""
        } else {
            Toast.makeText(context, "데이터 못 받아옴", Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Welcome to SOPT \n 테스트용 ID: $userId PW: $userPw")
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
                    // Handle the action when the "Next" button is clicked on the keyboard
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
            singleLine = true
        )
        Spacer(modifier = Modifier.height(120.dp))

        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                if (textid == userId && textpw == userPw && textid.isNotEmpty() && textpw.isNotEmpty()) {
                    Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("ID", textid)
                    intent.putExtra("PW", textpw)
                    intent.putExtra("NN", userId) // Assuming you have received this information from the signup screen
                    intent.putExtra("MBTI", userMBTI) // Assuming you have received this information from the signup screen


                    (context as Activity).setResult(Activity.RESULT_OK, intent)


                    context.finish()
                    context.startActivity(intent)
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
            } // Launch SignupActivity
        ) {
            Text(text = "회원가입 하기")
        }
    }
}
