package com.example.inventorymanagementapp.database

import com.example.inventorymanagementapp.database.orders.OrderApi
import com.example.inventorymanagementapp.database.products.ProductApi
import com.example.inventorymanagementapp.database.suppliers.SupplierApi
import com.example.inventorymanagementapp.database.warehouses.WarehouseApi
import com.example.inventorymanagementapp.database.login.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi : AuthApi = retrofit.create(AuthApi::class.java)
    val productApi: ProductApi = retrofit.create(ProductApi::class.java)
    val supplierApi: SupplierApi = retrofit.create(SupplierApi::class.java)
    val orderApi: OrderApi = retrofit.create(OrderApi::class.java)
    val warehouseApi: WarehouseApi = retrofit.create(WarehouseApi::class.java)
}