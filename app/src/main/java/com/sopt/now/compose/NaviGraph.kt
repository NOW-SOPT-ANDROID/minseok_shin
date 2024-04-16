package com.sopt.now.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


sealed class Routes(val route: String) {
    data object Home : Routes("Home")
    data object MyPage : Routes("MyPage")
    data object Search : Routes("Search")
}

@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@Composable
fun NaviGraph(navController: NavHostController,userId:String,userPassword:String,userNickname:String,userMBTI:String) {
    Log.d("NavGraph","NavGraph ")

    NavHost(navController = navController, startDestination = Routes.MyPage.route) {

        composable(route = Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Routes.MyPage.route) {
            MyPageScreen(navController,userId,userPassword,userNickname,userMBTI)
        }
        composable(route = Routes.Search.route) {
            SearchScreen(navController)
        }
    }

}