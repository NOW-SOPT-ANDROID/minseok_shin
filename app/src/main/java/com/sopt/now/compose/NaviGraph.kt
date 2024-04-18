package com.sopt.now.compose


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


sealed class Routes(val route: String) {
    data object Login : Routes("Login")
    data object Home : Routes("Home")
    data object Search : Routes("Search")
    data object SignUp : Routes("SignUp")
    data object MyPage : Routes("MyPage")
}

@Composable
fun NaviGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Login.route) {

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
        )
        {
            LoginScreen(
                navController = navController,
                userID = it.arguments?.getString("userID"),
                userPasswd = it.arguments?.getString("userPasswd"),
                userNickname = it.arguments?.getString("userNickname"),
                userMBTI = it.arguments?.getString("userMBTI")

            )
        }



        composable(route = Routes.MyPage.route + "/{userID}/{userPasswd}",
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType
                },
                navArgument(name = "userPasswd") {
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


        composable(route = Routes.SignUp.route) {
            SignupScreen(
                navController = navController
            )
        }
    }
}