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
import androidx.compose.ui.res.stringResource
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
        Spacer(modifier = Modifier.height(100.dp))
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace R.drawable.ic_launcher_foreground with your image resource
                contentDescription = "프로필 이미지",
            )
            Column {
                Text(text = "닉네임: $userNn")
                Text(text = "ID : $userId")
                Text(text = "Password: $userPw")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace R.drawable.ic_launcher_foreground with your image resource
            contentDescription = "MBTI 이미지",
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(16f / 9f)
        )
        Text(text = "MBTI: $userMBTI")
        Text(text = stringResource(id = R.string.))

    }
}


