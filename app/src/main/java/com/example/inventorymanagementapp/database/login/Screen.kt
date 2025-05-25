package com.example.inventorymanagementapp.database.login

sealed class Screen(val route : String) {
    object LoginScreen : Screen("login_screen")
    object SignupScreen : Screen("signup_screen")
}