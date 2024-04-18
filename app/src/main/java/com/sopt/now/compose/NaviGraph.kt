package com.sopt.now.compose


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object MyPage : Routes("MyPage")
    object Home : Routes("Home")
    object Search : Routes("Search")

}

@Composable
fun NaviGraph(
    navController: NavHostController,
    isLoggedIn: Boolean,
    onLoginSuccess: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(route = Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Routes.Search.route) {
            SearchScreen(navController)
        }

        composable(route = Routes.MyPage.route + "/{userID}/{userPasswd}/{userNickname}/{userMBTI}",
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType
                },
                navArgument(name = "userPasswd") {
                    type = NavType.StringType
                },
                navArgument(name = "userNickname") {
                    type = NavType.StringType
                },
                navArgument(name = "userMBTI") {
                    type = NavType.StringType
                }
            )
        ) {
            MyPageScreen(
                navController = navController,
                userID = it.arguments?.getString("userID"),
                userPasswd = it.arguments?.getString("userPasswd"),
                userNickname = it.arguments?.getString("userNickname"),
                userMBTI = it.arguments?.getString("userMBTI")
            )
        }

        composable(route = Routes.Login.route + "?userid={userID}&passwd={userPasswd}&nickname={userNickname}&mbti={userMBTI}",
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(name = "userPasswd") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(name = "userNickname") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(name = "userMBTI") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            val userID = navBackStackEntry.arguments?.getString("userID")
            val userPasswd = navBackStackEntry.arguments?.getString("userPasswd")
            val userNickname = navBackStackEntry.arguments?.getString("userNickname")
            val userMBTI = navBackStackEntry.arguments?.getString("userMBTI")

            LoginScreen(
                navController = navController,
                userID = userID,
                userPasswd = userPasswd,
                userNickname = userNickname,
                userMBTI = userMBTI,
                onLoginSuccess = onLoginSuccess
            )
        }

        composable(route = Routes.SignUp.route) {
            SignupScreen(navController = navController)
        }
    }
}
