package com.testforwork.ejercicio1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.testforwork.ejercicio1.presentation.userdetail.UserDetailScreen
import com.testforwork.ejercicio1.presentation.userlist.UserListScreen
import java.net.URLDecoder
import java.net.URLEncoder

object Routes {
    const val USER_LIST = "user_list"
    const val USER_DETAIL = "user_detail/{lat}/{long}/{name}"

    fun userDetail(lat: String, long: String, name: String): String {
        val encodedName = URLEncoder.encode(name, "UTF-8")
        return "user_detail/$lat/$long/$encodedName"
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.USER_LIST
    ) {
        composable(Routes.USER_LIST) {
            UserListScreen(
                onUserClick = { user ->
                    navController.navigate(
                        Routes.userDetail(user.latitude, user.longitude, user.fullName)
                    )
                }
            )
        }

        composable(
            route = Routes.USER_DETAIL,
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("long") { type = NavType.FloatType },
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            val long = backStackEntry.arguments?.getFloat("long")?.toDouble() ?: 0.0
            val encodedName = backStackEntry.arguments?.getString("name") ?: ""
            val name = URLDecoder.decode(encodedName, "UTF-8")

            UserDetailScreen(
                latitude = lat,
                longitude = long,
                userName = name,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}