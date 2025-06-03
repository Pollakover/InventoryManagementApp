package com.example.inventorymanagementapp.database.warehouses

import retrofit2.http.Body
import retrofit2.http.POST

interface WarehouseApi {
    @POST("warehouses/fetch")
    suspend fun fetchWarehouses(@Body request: FetchWarehousesRequest): List<Warehouse>

    @POST("/warehouses/add")
    suspend fun addWarehouse(@Body request: AddWarehouseRequest)

    @POST("/warehouses/getById")
    suspend fun getWarehouseById(@Body request: GetWarehouseByIdRequest): Warehouse
}

data class FetchWarehousesRequest(
    val user_login: String
)

data class AddWarehouseRequest(
    val name: String,
    val address: String,
    val postal_address: String,
    val user_login: String
)

data class GetWarehouseByIdRequest(
    val warehouseId: String
)
