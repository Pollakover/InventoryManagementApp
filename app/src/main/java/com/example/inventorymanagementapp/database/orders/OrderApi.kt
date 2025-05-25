package com.example.inventorymanagementapp.database.orders


import com.example.inventorymanagementapp.database.suppliers.AddSupplierRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {
    @POST("orders/fetch")
    suspend fun fetchOrders(@Body request: FetchOrdersRequest): List<Order>

    @POST("/orders/add")
    suspend fun addOrder(@Body request: AddOrderRequest)
}

data class FetchOrdersRequest(
    val user_login: String
)

data class AddOrderRequest(
    val amount: Int,
    val delivery_date: String,
    val user_login: String,
    val status: String,
    val product: String,
    val price: Double,
)