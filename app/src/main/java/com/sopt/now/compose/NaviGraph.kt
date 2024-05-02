package com.sopt.now.compose


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.screen.ChangePasswordScreen
import com.sopt.now.compose.screen.HomeScreen
import com.sopt.now.compose.screen.LoginScreen
import com.sopt.now.compose.screen.MyPageScreen
import com.sopt.now.compose.screen.SearchScreen
import com.sopt.now.compose.screen.SignupScreen

sealed class Routes(val route: String) {
    data object Login : Routes("Login")
    data object SignUp : Routes("SignUp")
    data object MyPage : Routes("MyPage")
    data object Home : Routes("Home")
    data object Search : Routes("Search")
    data object Password : Routes("Password")

}

@Composable
fun NaviGraph(
    navController: NavHostController, onLoginSuccess: (Boolean) -> Unit
) {
    val navStoreOwner = rememberViewModelStoreOwner()
    CompositionLocalProvider(
        LocalNavGraphViewModelStoreOwner provides navStoreOwner
    ) {
        NavHost(navController = navController, startDestination = Routes.Login.route) {

            composable(route = Routes.Home.route) {
                HomeScreen()
            }
            composable(route = Routes.Search.route) {
                SearchScreen()
            }

            composable(
                route = Routes.MyPage.route,
            ) {
                MyPageScreen(navController = navController)
            }

            composable(
                route = Routes.Password.route,
            ) {
                ChangePasswordScreen(navController = navController)
            }

            composable(
                route = Routes.Login.route,
            ) {
                LoginScreen(
                    navController = navController, onLoginSuccess = onLoginSuccess
                )
            }

            composable(route = Routes.SignUp.route) {
                SignupScreen(navController = navController)
            }
        }
    }
}
