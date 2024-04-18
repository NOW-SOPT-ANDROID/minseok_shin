package com.sopt.now.compose


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Routes(val route: String) {
    data object Login : Routes("Login")
    data object SignUp : Routes("SignUp")
    data object MyPage : Routes("MyPage")
    data object Home : Routes("Home")
    data object Search : Routes("Search")

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
                MyPageScreen()
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
