package com.sopt.now.compose.screen

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.Routes
import com.sopt.now.compose.data.model.RequestLogInDto
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.compose.viewmodel.LocalNavGraphViewModelStoreOwner
import com.sopt.now.compose.viewmodel.LoginViewModel
import com.sopt.now.compose.viewmodel.NavViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    onLoginSuccess: (Boolean) -> Unit
) {


    var textId by remember { mutableStateOf("") }
    var textPw by remember { mutableStateOf("") }
    val context = LocalContext.current

    val loginViewModel: LoginViewModel = viewModel()

    val navViewModel: NavViewModel = viewModel(
        viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(loginViewModel.loginState, lifecycleOwner) {
        loginViewModel.loginState.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { loginState ->
                when (loginState) {
                    is LoginViewModel.LoginState.Success<*> -> {
                        Toast.makeText(
                            context,
                            "로그인 성공 memberID: ${loginState.data}",
                            Toast.LENGTH_SHORT
                        ).show()

                        navViewModel.memberId = loginState.data as Int
                        onLoginSuccess(true)
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                        Log.d("LoginActivity", "memberId of viewModel :  ${loginState.data}")

                    }

                    is LoginViewModel.LoginState.Error -> {

                    }

                    else -> Unit
                }
            }
    }

    fun login() {
        val id = textId
        val password = textPw
        val loginRequest = RequestLogInDto(id, password)

        loginViewModel.login(loginRequest)
    }

    NOWSOPTAndroidTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Welcome to SOPT")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textId,
                onValueChange = { textId = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("사용자 이름 입력") },
                placeholder = { Text("") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                    }
                )
            )
            TextField(
                value = textPw,
                onValueChange = { textPw = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text("비밀번호 입력") },
                placeholder = { Text("") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(120.dp))

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    login()
                }
            ) {
                Text(text = "로그인 하기")
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(Routes.SignUp.route)
                }
            ) {
                Text(text = "회원가입 하기")
            }
        }
    }
}
