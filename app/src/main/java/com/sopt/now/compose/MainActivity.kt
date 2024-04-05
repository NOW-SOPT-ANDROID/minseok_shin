package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                val userId = intent.getStringExtra("ID") ?: "DefaultUserID"
                val userPw = intent.getStringExtra("PW") ?: "DefaultPassword"
                val userNn = intent.getStringExtra("NN") ?: "DefaultNickname"
                val userMBTI = intent.getStringExtra("MBTI") ?: "DefaultMBTI"
                MainScreen(userId, userPw, userNn, userMBTI)
            }
        }
    }
}
@Composable
fun MainScreen(userId: String, userPw: String, userNn: String, userMBTI: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Image 1",
                modifier = Modifier.padding(16.dp).aspectRatio(0.5f)
            )
            Column {
                Text(text = "닉네임: $userNn")
                Text(text = "ID: $userId")
                Text(text = "Password: $userPw")
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace R.drawable.ic_launcher_foreground with your image resource
                contentDescription = "Image 2",
                modifier = Modifier.aspectRatio(0.5f) //
            )
        }
        Text(text = "MBTI: $userMBTI", modifier = Modifier.padding(top = 16.dp))
    }
}



