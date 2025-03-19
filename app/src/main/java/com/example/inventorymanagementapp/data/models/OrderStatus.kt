package com.example.inventorymanagementapp.data.models

import androidx.compose.ui.graphics.Color
import com.example.inventorymanagementapp.ui.theme.*

enum class OrderStatus(val color : Color, val text : String) {
    RECIEVED(success_500, "Получен"),
    IN_TRANSIT(primary_500, "В пути"),
    LATE(warning_500, "Задерживается"),
    CANCELED(error_500, "Отменен")
}