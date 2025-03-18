package com.example.inventorymanagementapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


class MainViewModel : ViewModel() {
    var searchButtonState by mutableStateOf(false)
        private set

    fun changeButtonState() {
        searchButtonState = !searchButtonState
        _searchText.value = TextFieldValue("")
    }

    var focusRequester by mutableStateOf(FocusRequester())

    fun requestSearchFocus() {
        focusRequester.requestFocus()
    }

    private val _searchText = MutableStateFlow(TextFieldValue(""))
    val searchText = _searchText.asStateFlow()

    private val _products = MutableStateFlow(allProducts)
    val products = searchText
        .combine(_products) { text, products ->
            if (text.text.isBlank()) {
                products
            } else {
                products.filter {
                    it.doesMatchSearchQuery(text.text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _products.value
        )

    fun onSearchTextChange(text: TextFieldValue) {
        _searchText.value = text
    }
}

val testProduct = Product(
    name = "Кофе Нескафе Gold растворимый 190г",
    price = 649.99,
    amount = 5,
    expiryDate = "10.09.25",
    amountSold = 123,
    image = R.drawable.test
)

val testProduct1 = Product(
    name = "Яйцо куриное Окское",
    price = 119.99,
    amount = 22,
    expiryDate = "10.09.25",
    amountSold = 43,
    image = R.drawable.eggs
)

val testProduct2 = Product(
    name = "Шампунь Шаума Men",
    price = 249.99,
    amount = 0,
    expiryDate = "11.12.25",
    amountSold = 123,
    image = R.drawable.shampoo
)

private val allProducts = listOf(testProduct, testProduct1, testProduct2)