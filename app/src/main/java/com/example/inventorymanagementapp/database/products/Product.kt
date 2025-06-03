package com.example.inventorymanagementapp.database.products

data class Product(
    val productId: String,
    override val name: String,
    val amount_sold: Int,
    val category: String,
    val price: Double,
    val user_login: String,
    val supplier: String,
    val warehouse: String,
    val image_data: String,
    val amount: Int,
) : Searchable