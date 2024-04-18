package com.sopt.now.compose

import android.support.annotation.StringRes
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

}
sealed class BottomNavRoutes(val route: String, @StringRes val resourceId: Int) {
    object Home : BottomNavRoutes("Home", R.string.home)
    object Search : BottomNavRoutes("Search", R.string.search)
    object MyPage : BottomNavRoutes("MyPage", R.string.mypage)
}

val navItems = listOf(
    BottomNavRoutes.Home,
    BottomNavRoutes.Search,
    BottomNavRoutes.MyPage
)

@Composable
fun NaviGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(route = BottomNavRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomNavRoutes.Search.route) {
            SearchScreen(navController)
        }

//        composable(route = BottomNavRoutes.MyPage.route) {
//            MyPageScreen(navController)
//        }
        composable(route = BottomNavRoutes.MyPage.route + "/{userID}/{userPasswd}/{userNickname}/{userMBTI}",
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
        composable(route = Routes.SignUp.route) {
            SignupScreen(
                navController = navController
            )
        }


    }
}

