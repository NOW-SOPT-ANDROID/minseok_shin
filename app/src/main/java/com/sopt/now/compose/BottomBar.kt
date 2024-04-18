package com.sopt.now.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean
) {

    val screens = listOf(
        Destinations.Home, Destinations.Search, Destinations.MyPage
    )

    if (isLoggedIn) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavigationBar(
            modifier = modifier,
            containerColor = Color.LightGray,
        ) {
            screens.forEach { screen ->

                NavigationBarItem(
                    label = {
                        Text(text = screen.title!!)
                    },
                    icon = {
                        Icon(imageVector = screen.icon!!, contentDescription = "")
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedTextColor = Color.Gray, selectedTextColor = Color.White
                    ),
                )
            }
        }
    }

}

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Home : Destinations(
        route = Routes.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search : Destinations(
        route = Routes.Search.route,
        title = "Search",
        icon = Icons.Default.Search
    )

    object MyPage : Destinations(
        route = Routes.MyPage.route,
        title = "MyPage",
        icon = Icons.Default.Person
    )

}
