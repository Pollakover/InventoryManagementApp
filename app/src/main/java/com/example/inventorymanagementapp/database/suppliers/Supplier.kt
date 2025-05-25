package com.example.inventorymanagementapp.database.suppliers

data class Supplier (
    val supplierId: String,
    val name: String,
    val phone_number: String,
    val user_login: String,
    val type: String,
    val products: List<String> = emptyList()
){

    fun allProducts(): String {
        return products.joinToString(", ")
    }
}