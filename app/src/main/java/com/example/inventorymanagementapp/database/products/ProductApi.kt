package com.example.inventorymanagementapp.database.products

import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApi {
    @POST("products/fetch")
    suspend fun fetchProducts(@Body request: FetchProductsRequest): List<Product>

    @POST("/products/add")
    suspend fun addProduct(@Body request: AddProductRequest)
}

data class FetchProductsRequest(
    val user_login: String
)

data class AddProductRequest(
    val name: String,
    val amount_sold: Int,
    val category: String,
    val price: Double,
    val user_login: String,
    val supplier: String,
    val warehouse: String,
    val image_data: String?,
    val amount: Int,
)