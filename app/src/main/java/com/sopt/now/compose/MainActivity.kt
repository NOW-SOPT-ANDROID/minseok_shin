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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                MainScreen()
            }
        }
    }
}
@Composable
fun MainScreen() {
    var userId by remember { mutableStateOf("못받음") }
    var userPw by remember { mutableStateOf("") }
    var userNn by remember { mutableStateOf("") }
    var userMBTI by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            userId = data?.getStringExtra("ID") ?: ""
            userPw = data?.getStringExtra("PW") ?: ""
            userNn = data?.getStringExtra("NN") ?: ""
            userMBTI = data?.getStringExtra("MBTI") ?: ""
        }
    }


    Column {
        Text(text = "ID : $userId")
        Text(text = "Password: $userPw")
        Text(text = "닉네임: $userNn")
        Text(text = "MBTI: $userMBTI")
    }
}


