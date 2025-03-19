package com.example.inventorymanagementapp.data.models

data class Supplier(
    override val name: String,
    val phoneNumber: String,
    val type: String,
    val suppliedProducts: List<Product>
) : Searchable {

    fun allProducts(): String {
        var productsNames = ""
        for (product in suppliedProducts) {
            if (product != suppliedProducts.last()) {
                productsNames = productsNames + product.name + ", "
            } else {
                productsNames += product.name
            }
        }
        return productsNames
    }
}
