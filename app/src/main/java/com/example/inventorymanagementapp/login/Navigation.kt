package com.example.inventorymanagementapp.login

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignupScreen.route) {
            SignUpScreen(navController = navController)
        }
    }
}