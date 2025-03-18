package com.example.inventorymanagementapp.data.models

data class Product(
    override val name: String,
    val price: Double,
    val amount: Int,
    val expiryDate: String,
    val amountSold: Int,
    val image: Int
) : Searchable
