package com.example.inventorymanagementapp.database.orders

data class Order(
    val orderId: String,
    val amount: Int,
    val delivery_date: String,
    val user_login: String,
    val status: String,
    val number: Int,
    val product: String,
    val price: Double
)

