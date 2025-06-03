package com.example.inventorymanagementapp.database.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.inventorymanagementapp.MainActivity
import com.example.inventorymanagementapp.ui.theme.InventoryManagementAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        val savedLogin = sharedPreferences.getString("user_login", null)

        if (!savedLogin.isNullOrEmpty()) {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("USER_LOGIN", savedLogin)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
            return
        }

        setContent {
            InventoryManagementAppTheme(darkTheme = isDarkMode) {
                Navigation()
            }
        }
    }
}