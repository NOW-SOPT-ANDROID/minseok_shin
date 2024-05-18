package com.sopt.now.compose.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.Friend
import com.sopt.now.compose.LocalNavGraphViewModelStoreOwner
import com.sopt.now.compose.MyProfile
import com.sopt.now.compose.NavViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.ServicePool.authService
import com.sopt.now.compose.data.ResponseInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun HomeScreen() {

    val navViewModel: NavViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)

    val myImage = R.drawable.baseline_person_24
    var myName by remember {
        mutableStateOf("")
    }
    val ybDescription = stringResource(id = R.string.yb)
    val obDescription = stringResource(id = R.string.ob)
    val partDescription = stringResource(id = R.string.part)
    val friendImage = R.drawable.baseline_person_outline_24

    val context = LocalContext.current

    fun searchInfo(memberId: Int) {
        authService.info(memberId).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>
            ) {
                if (response.isSuccessful) {
                    myName = response.body()!!.data.nickname
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

    val friendList = listOf(
        Friend(
            profileImageRes = friendImage,
            name = "강문수",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "공세영",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "김명석",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "김아린",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "김언지",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "김윤서",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "박동민",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "박유진",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "배지현",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "배찬우",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "손민재",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "송혜음",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "우상욱",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "유정현",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "윤서희",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "곽의진",
            description = partDescription
        ), Friend(
            profileImageRes = friendImage, name = "이가을",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "이나경",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "이석준",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "이석찬",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "이연진",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "이유빈",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "임하늘",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "주효은",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "최준서",
            description = obDescription
        ), Friend(
            profileImageRes = friendImage, name = "이현진",
            description = ybDescription
        ), Friend(
            profileImageRes = friendImage, name = "박효빈",
            description = ybDescription
        )
    )


    val myProfile = MyProfile(
        profileImageRes = myImage,
        name = myName,
        description = ybDescription
    )

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxSize()
    ) {
        item {
            MyProfileItem(myProfile)
        }

        items(friendList) { friend ->
            FriendProfileItem(friend)
        }
    }
}

@Composable
fun MyProfileItem(myProfile: MyProfile) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = myProfile.profileImageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = myProfile.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = myProfile.description,
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
    }
}

@Composable
fun FriendProfileItem(friend: Friend) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = friend.profileImageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = friend.name,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
            )
            Text(
                text = friend.description,
                color = Color.DarkGray,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
    }
}
