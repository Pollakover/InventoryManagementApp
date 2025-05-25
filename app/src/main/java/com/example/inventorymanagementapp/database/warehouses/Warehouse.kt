package com.example.inventorymanagementapp.database.warehouses

data class Warehouse (
    val warehouseId: String,
    val name: String,
    val address: String,
    val postal_address: String,
    val user_login: String
)