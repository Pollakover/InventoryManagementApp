package com.example.inventorymanagementapp.data.models

data class Supplier(
    override val name: String,
    val phoneNumber: String,
    val type : String,
    val suppliedProducts: List<Product>
) : Searchable
{
    fun allProducts() : String {
        var products = ""

        for (product in suppliedProducts) {
            if (product != suppliedProducts.last()){
                products = products + product.name + ", "
            }
            else{
                products += product.name
            }
        }
        return products
    }
}
