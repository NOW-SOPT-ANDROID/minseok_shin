package com.sopt.now.compose.screen

import ChangePasswordViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.Routes
import com.sopt.now.compose.viewmodel.LocalNavGraphViewModelStoreOwner
import com.sopt.now.compose.viewmodel.NavViewModel

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    changePasswordViewModel: ChangePasswordViewModel = viewModel()
) {

    var previousPassword by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var newPasswordCheck by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val navViewModel: NavViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)

    fun changePassword(memberId: Int) {
        changePasswordViewModel.changePassword(
            memberId,
            previousPassword,
            newPassword,
            newPasswordCheck
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Welcome to SOPT")
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = previousPassword,
            onValueChange = { previousPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("기존 비밀번호 입력") },
            placeholder = { Text("") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                }
            )
        )
        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("새 비밀번호 입력") },
            placeholder = { Text("") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        TextField(
            value = newPasswordCheck,
            onValueChange = { newPasswordCheck = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("새 비밀번호 확인") },
            placeholder = { Text("") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                changePassword(navViewModel.memberId)
                Toast.makeText(
                    context,
                    "비밀번호 변경 완료! 새 비밀번호로 로그인 하세요",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Login.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        ) {
            Text(text = "비밀번호 변경")
        }

    }
}