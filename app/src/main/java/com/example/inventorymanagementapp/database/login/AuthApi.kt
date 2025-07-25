package com.example.inventorymanagementapp.database.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<AuthResponse>

    @POST("/user/getByLogin")
    suspend fun getUserByLogin(@Body request: GetUserByLoginRequest): UserResponse
}