package com.sopt.now.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    private val nameId = "ID"
    private val namePassword = "PASSWORD"
    private val nameNickname = "NICKNAME"
    private val nameMBTI = "MBTI"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Log.d("MainActivity","MainActivity onCreate")
                val userId = intent.getStringExtra(nameId) ?: "DefaultUserID"
                val userPw = intent.getStringExtra(namePassword) ?: "DefaultPassword"
                val userNn = intent.getStringExtra(nameNickname) ?: "DefaultNickname"
                val userMBTI = intent.getStringExtra(nameMBTI) ?: "DefaultMBTI"
                Log.d("MainActivity","get StringData")
                val navController = rememberNavController()
                NaviGraph(navController,userId,userPw,userNn,userMBTI)
            }
        }
    }
}





