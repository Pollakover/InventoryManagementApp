package com.example.inventorymanagementapp.data.models

import androidx.compose.ui.graphics.Color

data class Warehouse(
    val name: String,
    val address: String,
    val postalCode: String,
    val color: Color
)