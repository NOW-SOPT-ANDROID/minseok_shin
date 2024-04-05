package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val imageResource = when (userMBTI.uppercase()) {
        "ENFJ" -> R.drawable.enfj
        "ENFP" -> R.drawable.enfp
        "ENTJ" -> R.drawable.entj
        "ENTP" -> R.drawable.entp
        "ESFJ" -> R.drawable.esfj
        "ESFP" -> R.drawable.esfp
        "ESTJ" -> R.drawable.estj
        "ESTP" -> R.drawable.estp
        "INFJ" -> R.drawable.infj
        "INFP" -> R.drawable.infp
        "INTJ" -> R.drawable.intj
        "INTP" -> R.drawable.intp
        "ISFJ" -> R.drawable.isfj
        "ISFP" -> R.drawable.isfp
        "ISTJ" -> R.drawable.istj
        "ISTP" -> R.drawable.istp
        else -> R.drawable.ic_launcher_foreground // Use a default image if userMBTI doesn't match any specific case
    }

    val textResource = when (userMBTI.uppercase()) {
        "ENFJ" -> R.string.enfj
        "ENFP" -> R.string.enfp
        "ENTJ" -> R.string.entj
        "ENTP" -> R.string.entp
        "ESFJ" -> R.string.esfj
        "ESFP" -> R.string.esfp
        "ESTJ" -> R.string.estj
        "ESTP" -> R.string.estp
        "INFJ" -> R.string.infj
        "INFP" -> R.string.infp
        "INTJ" -> R.string.intj
        "INTP" -> R.string.intp
        "ISFJ" -> R.string.isfj
        "ISFP" -> R.string.isfp
        "ISTJ" -> R.string.istj
        "ISTP" -> R.string.istp
        else -> R.string.error // Use a default text resource if userMBTI doesn't match any specific case
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace R.drawable.ic_launcher_foreground with your image resource
                contentDescription = "프로필 이미지",
                modifier = Modifier.background(Color.LightGray)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "닉네임: $userNn")
                Text(text = "ID : $userId")
                Text(text = "Password: $userPw")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "MBTI 이미지",
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(16f / 9f)

        )
        Text(text = "MBTI: ${userMBTI.uppercase()}")
        Text(text = stringResource(id = textResource))

    }
}


