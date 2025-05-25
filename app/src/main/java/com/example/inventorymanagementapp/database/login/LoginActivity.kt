package com.example.inventorymanagementapp.database.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.inventorymanagementapp.ui.theme.InventoryManagementAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryManagementAppTheme(darkTheme = false) {
                Navigation()
            }
        }
    }
}