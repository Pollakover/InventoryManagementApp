package com.example.inventorymanagementapp.database.suppliers

import retrofit2.http.Body
import retrofit2.http.POST

interface SupplierApi {
    @POST("suppliers/fetch")
    suspend fun fetchSuppliers(@Body request: FetchSuppliersRequest): List<Supplier>

    @POST("/suppliers/add")
    suspend fun addSupplier(@Body request: AddSupplierRequest)

    @POST("/suppliers/getById")
    suspend fun getSupplierById(@Body request: GetSupplierByIdRequest): Supplier
}

data class FetchSuppliersRequest(
    val user_login: String
)

data class AddSupplierRequest(
    val name: String,
    val phone_number: String,
    val type: String,
    val user_login: String,
)

data class GetSupplierByIdRequest(
    val supplierId: String
)