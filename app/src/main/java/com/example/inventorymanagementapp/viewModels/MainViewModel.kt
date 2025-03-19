package com.example.inventorymanagementapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.data.models.Order
import com.example.inventorymanagementapp.data.models.OrderStatus
import com.example.inventorymanagementapp.data.models.Product
import com.example.inventorymanagementapp.data.models.Supplier
import com.example.inventorymanagementapp.data.models.Warehouse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random


class MainViewModel : ViewModel() {

    var searchButtonState by mutableStateOf(false)
        private set

    // changes button state
    fun changeButtonState() {
        searchButtonState = !searchButtonState
        _searchText.value = TextFieldValue("")
    }
    
    //Focus request for search bar
    var focusRequester by mutableStateOf(FocusRequester())
    
    fun requestSearchFocus() {
        focusRequester.requestFocus()
    }

    // Сбрасывает фокус
    fun clearSearchFocus() {
        focusRequester.freeFocus()
    }

    private val _searchText = MutableStateFlow(TextFieldValue(""))
    val searchText = _searchText.asStateFlow()

    //Products
    private val _products = MutableStateFlow(allProducts)
    val products = searchText
        .combine(_products) { query, products ->
            if (query.text.isBlank()) {
                products
            } else {
                products.filter {
                    it.doesMatchSearchQuery(query.text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _products.value
        )

    //Suppliers
    private val _suppliers = MutableStateFlow(allSuppliers)
    val suppliers = searchText
        .combine(_suppliers) { query, suppliers ->
            if (query.text.isBlank()) {
                suppliers
            } else {
                suppliers.filter {
                    it.doesMatchSearchQuery(query.text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _suppliers.value
        )

    //Orders
    private val _orders = MutableStateFlow(allOrders)
    val orders = searchText
        .combine(_orders) { query, orders ->
            if (query.text.isBlank()) {
                orders
            } else {
                orders.filter {
                    it.doesMatchSearchQuery(query.text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _orders.value
        )

    //Warehoses
    private val _warehouses = MutableStateFlow(allWarehouses)
    val warehouses = searchText
        .combine(_warehouses) { _, warehouses ->
                warehouses
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _warehouses.value
        )

    var error by mutableStateOf(false)

    fun onSearchTextChange(text: TextFieldValue) {
        _searchText.value = text

        //Случайный вызов ошибки.

        if(Random.nextInt(0, 10) == 1 && _searchText.value.text.isNotBlank()) {
            error = true
        }
    }

    fun clearSearch() {
        _searchText.value = TextFieldValue("")
    }
}


//Products placeholder
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


//Suppliers placeholder
val supplier1 = Supplier(
    name = "Ronald Martin",
    phoneNumber = "7687764556",
    type = "Принимает возврат",
    suppliedProducts = allProducts
)

val supplier2 = Supplier(
    name = "Tom Homan",
    phoneNumber = "9867545368",
    type = "Не принимает возврат",
    suppliedProducts = listOf(
        Product(
            name = "Maaza",
            price = 143.123,
            amount = 123,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        )
    )
)

val supplier3 = Supplier(
    name = "Fainden Juke",
    phoneNumber = "9567545769",
    type = "Принимает возврат",
    suppliedProducts = listOf(
        Product(
            name = "Marie Gold",
            price = 143.123,
            amount = 123,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        )
    )
)

val supplier4 = Supplier(
    name = "Dender Luke",
    phoneNumber = "9567545769",
    type = "Принимает возврат",
    suppliedProducts = listOf(
        Product(
            name = "Saffola",
            price = 143.123,
            amount = 123,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        ),
        Product(
            name = "Dairy Milk",
            price = 143.123,
            amount = 123,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        )
    )
)

private val allSuppliers = listOf(supplier1, supplier2, supplier3, supplier4)




//Orders placeholder
val order1 = Order(
    name = "№ 38762682-0172",
    products = listOf(
        Product(
            name = "Magi",
            price = 143.99,
            amount = 123,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        ),
        Product(
            name = "Dairy Milk",
            price = 1313.99,
            amount = 123,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        )
    ),
    deliveryDate = "12.03.25",
    status = OrderStatus.RECIEVED
)

val order2 = Order(
    name = "№ 38762682-0173",
    products = listOf(
        Product(
            name = "Red Bull",
            price = 255.99,
            amount = 100,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        )
    ),
    deliveryDate = "25.03.25",
    status = OrderStatus.IN_TRANSIT
)

val order3 = Order(
    name = "№ 38762682-0174",
    products = listOf(
        Product(
            name = "Хлеб",
            price = 55.99,
            amount = 150,
            expiryDate = "12.10.2025",
            amountSold = 23,
            image = R.drawable.container_icon,
        )
    ),
    deliveryDate = "19.04.25",
    status = OrderStatus.CANCELED
)

val order4 = Order(
    name = "№ 38762682-0175",
    products = listOf(
        testProduct,
        testProduct1
    ),
    deliveryDate = "13.01.25",
    status = OrderStatus.LATE
)

private val allOrders = listOf(order1, order2, order3, order4)



//Warehouses placeholder
val warehouse1 = Warehouse(
    name = "Склад 1",
    address = "г. Москва, ул. Покровка, 27, стр. 6",
    postalCode = "044-653578",
    color = Color(0xFF553BE5),
)

val warehouse2 = Warehouse(
    name = "Склад 2",
    address = "г. Москва, ул. Покровка, 27, стр. 6",
    postalCode = "044-653578",
    color = Color(0xFF22697B),
)

val warehouse3 = Warehouse(
    name = "Склад 3",
    address = "г. Москва, ул. Покровка, 27, стр. 6",
    postalCode = "044-653578",
    color = Color(0xFFEFDB69),
)

private val allWarehouses = listOf(warehouse1, warehouse2, warehouse3)