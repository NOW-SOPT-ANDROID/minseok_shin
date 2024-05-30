package com.sopt.now.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.Routes

import com.sopt.now.compose.viewmodel.LocalNavGraphViewModelStoreOwner
import com.sopt.now.compose.viewmodel.MyPageViewModel
import com.sopt.now.compose.viewmodel.NavViewModel

@Composable
fun MyPageScreen(navController: NavHostController, onLoginSuccess: (Boolean) -> Unit) {
    val navViewModel: NavViewModel = viewModel(
        viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current
    )

    val myPageViewModel: MyPageViewModel = viewModel()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        myPageViewModel.searchInfo(navViewModel.memberId)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "프로필 이미지",
                modifier = Modifier.background(Color.LightGray)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "닉네임: ${myPageViewModel.userNickname}")
                Text(text = "ID : ${myPageViewModel.userId}")
                Text(text = "Phone: ${myPageViewModel.userPhone}")
                Spacer(modifier = Modifier.padding(20.dp))
                Button(onClick = {
                    onLoginSuccess(false)
                    navController.navigate(Routes.Password.route)
                }) {
                    Text(text = "비밀번호 변경")
                }
            }
        }
    }
}
