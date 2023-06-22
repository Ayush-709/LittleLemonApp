package com.example.littlelemonapplication

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, sharedPreferences: SharedPreferences, menuItemDao: MenuItemDao) {
    val isUserRegistered = sharedPreferences.getBoolean("isRegistered", false)

    NavHost(
        navController = navController,
        startDestination = determineStartDestination(sharedPreferences),
    ) {
        if (!isUserRegistered) {
            composable(OnBoard.route) {
                UserOnBoarding(navController, sharedPreferences,menuItemDao)
            }
        }
        composable(Home.route) {
            HomeScreen(navController, menuItemDao = menuItemDao)
        }
        composable(Profile.route) {
            ProfileScreen(navController)
        }
    }
}

fun determineStartDestination(sharedPreferences: SharedPreferences): String {
    val isUserRegistered = sharedPreferences.getBoolean("isRegistered", false)
    return if(isUserRegistered){
        Home.route
    }else{
        OnBoard.route
    }

}
