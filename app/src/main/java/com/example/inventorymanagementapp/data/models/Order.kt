package com.example.inventorymanagementapp.data.models

import androidx.compose.ui.graphics.Color
import com.example.inventorymanagementapp.ui.theme.*
import java.math.RoundingMode
import kotlin.math.roundToInt
import kotlin.math.round

class Order(
    override val name: String,
    val products: List<Product>,
    val deliveryDate: String,
    val status: OrderStatus
) : Searchable {

    private var amount = 0
    private var price = 0.0

    fun calculatePrice(): String {
        for (product in products) {
            price += product.amount * product.price
            //price += product.price
        }
        price = price.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        return price.toString()
    }

    fun calculateAmount(): String {
        for (product in products) {
            amount += product.amount
        }
        return amount.toString()
    }

    fun allProducts(): String {
        var productsNames = ""
        for (product in products) {
            if (product != products.last()) {
                productsNames = productsNames + product.name + ", "
            } else {
                productsNames += product.name
            }
        }
        return productsNames
    }
}