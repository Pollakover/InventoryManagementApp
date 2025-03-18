package com.example.inventorymanagementapp.data.models

data class Product(
    val name : String,
    val price : Double,
    val amount : Int,
    val expiryDate: String,
    val amountSold : Int,
    val image: Int
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
