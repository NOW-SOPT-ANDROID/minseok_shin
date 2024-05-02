package com.sopt.now.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.ServicePool.authService
import com.sopt.now.compose.data.ResponseInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MyPageScreen() {
    val navViewModel: NavViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)

    val context = LocalContext.current

    var userId by remember {
        mutableStateOf("")
    }
    var userNickname by remember {
        mutableStateOf("")
    }
    var userPhone by remember {
        mutableStateOf("")
    }

    Log.d("MyPageScreen", "MyPageScreen start")

    fun searchInfo(memberId: Int) {
        authService.info(memberId).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>
            ) {
                if (response.isSuccessful) {
                    userId = response.body()!!.data.authenticationId
                    userNickname = response.body()!!.data.nickname
                    userPhone = response.body()!!.data.phone

                } else {
                    Log.d("HomeActivity", "response ${response.body()?.message}")
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                Toast.makeText(context, "조회 요청 실패: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    LaunchedEffect(Unit) {
        searchInfo(navViewModel.memberId)
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
                Text(text = "닉네임: $userNickname")
                Text(text = "ID : $userId")
                Text(text = "Phone: $userPhone")
            }
        }


    }
}
