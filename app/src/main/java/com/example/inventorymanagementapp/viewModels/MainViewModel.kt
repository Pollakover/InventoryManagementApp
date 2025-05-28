package com.example.inventorymanagementapp.viewModels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanagementapp.database.orders.AddOrderRequest
import com.example.inventorymanagementapp.database.orders.FetchOrdersRequest
import com.example.inventorymanagementapp.database.products.FetchProductsRequest
import com.example.inventorymanagementapp.database.products.Product
import com.example.inventorymanagementapp.database.suppliers.FetchSuppliersRequest
import com.example.inventorymanagementapp.database.orders.Order
import com.example.inventorymanagementapp.database.products.AddProductRequest
import com.example.inventorymanagementapp.database.suppliers.AddSupplierRequest
import com.example.inventorymanagementapp.database.suppliers.Supplier
import com.example.inventorymanagementapp.database.warehouses.FetchWarehousesRequest
import com.example.inventorymanagementapp.database.warehouses.Warehouse
import com.example.inventorymanagementapp.database.ApiClient
import com.example.inventorymanagementapp.database.warehouses.AddWarehouseRequest
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.core.content.edit

class MainViewModel(
    private val sharedPreferences: SharedPreferences,
    private val userLogin: String
) : ViewModel() {

    //Изменение темы
    var isDarkModeOn by mutableStateOf(
        sharedPreferences.getBoolean("dark_mode", false)
    )
        private set

    fun changeTheme() {
        isDarkModeOn = !isDarkModeOn
        sharedPreferences.edit {
            putBoolean("dark_mode", isDarkModeOn)
        }
    }

    // Поток для хранения истории поиска
    private val _searchHistory = MutableStateFlow<List<TextFieldValue>>(emptyList())
    val searchHistory: StateFlow<List<TextFieldValue>> = _searchHistory

    init {
        loadSearchHistory()
    }

    // Загружает историю поиска из SharedPreferences
    private fun loadSearchHistory() {
        val history = sharedPreferences.getStringSet("search_history", emptySet())
            ?.map { TextFieldValue(it) }
            ?.sortedByDescending { it.text } // Сортировка, если вдруг порядок нарушен
            ?: emptyList()
        _searchHistory.value = history
    }

    // Сохраняет новый поисковый запрос
    fun saveSearchQuery(query: TextFieldValue) {
        val history = _searchHistory.value.toMutableList()

        // Удаляем запрос, если он уже есть в истории (чтобы избежать дублирования)
        history.remove(query)
        history.add(0, query) // Добавляем в начало списка

        // Ограничиваем размер истории
        if (history.size > 11) {
            history.removeAt(history.lastIndex) // Удаляем самый старый элемент
        }

        _searchHistory.value = history
        sharedPreferences.edit()
            .putStringSet("search_history", history.map { it.text }.toSet())
            .apply()
    }

    // Очищает историю поиска
    fun clearSearchHistory() {
        sharedPreferences.edit().remove("search_history").apply()
        _searchHistory.value = emptyList() // Обновляем UI
    }

    //Состояние кнопки поиска
    var searchButtonState by mutableStateOf(false)
        private set

    // изменения состояния кнопки поиска
    fun changeButtonState() {
        _searchText.value = TextFieldValue("")
        searchButtonState = !searchButtonState
    }

    //Запрос фокуса
    var focusRequester by mutableStateOf(FocusRequester())
    
    fun requestSearchFocus() {
        focusRequester.requestFocus()
    }

    //Для задержки при поиске
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    var isFocused by mutableStateOf(false)

    //Текст внутри поля
    private val _searchText = MutableStateFlow(TextFieldValue(""))
    val searchText = _searchText.asStateFlow()

//Products
/////////////////////////////////////////////////////////////////////////////////////////////////

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    var error by mutableStateOf(false)

    val _products = MutableStateFlow<List<Product>>(emptyList())

    @OptIn(FlowPreview::class)
    val products = searchText
        .debounce(1000L)
        .onEach { query ->
            _isSearching.update { true }
            saveSearchQuery(query)
        }
        .combine(_products) { query, products ->
            if (query.text.isBlank()) {
                products
            } else {
                delay(2000L)
                products.filter {
                    it.doesMatchSearchQuery(query.text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _products.value
        )


    fun loadProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiClient.productApi.fetchProducts(FetchProductsRequest(userLogin))
                _products.value = response // Обновляем _products напрямую
                error = false
            } catch (_: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _addProductSuccess = MutableStateFlow(false)

    fun addProduct(
        name: String,
        amount_sold: Int,
        category: String,
        price: Double,
        supplier: String,
        warehouse: String,
        image_data: String,
        amount: Int
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                ApiClient.productApi.addProduct(
                    AddProductRequest(
                        name = name,
                        amount_sold = amount_sold,
                        category = category,
                        price = price,
                        user_login = userLogin,
                        supplier = supplier,
                        warehouse = warehouse,
                        image_data = image_data,
                        amount = amount
                    )
                )
                _addProductSuccess.value = true
                error = false
            } catch (e: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getLowStockProducts(): List<Product> {
        return _products.value
            //.filter { it.amount > 0 }
            .sortedBy { it.amount }
            .take(3)
    }

    fun getHighDemandProducts(): List<Product> {
        return _products.value
            .filter { it.amount_sold > 0 }
            .sortedByDescending { it.amount_sold }
            .take(3)
    }

    fun countProducts() : Int {
        var amount = 0
        for (product in _products.value) {
            amount += 1
        }
        return amount
    }

    fun countProductsInStock() : Int {
        var amount = 0
        for (product in _products.value) {
            if(product.amount >= 10) {
                amount += 1
            }
        }
        return amount
    }

    fun countProductsLowInStock() : Int {
        var amount = 0
        for (product in _products.value) {
            if(product.amount < 10 && product.amount != 0) {
                amount += 1
            }
        }
        return amount
    }

    fun countProductsNotInStock() : Int {
        var amount = 0
        for (product in _products.value) {
            if(product.amount == 0) {
                amount += 1
            }
        }
        return amount
    }

/////////////////////////////////////////////////////////////////////////////////////////////////

//Suppliers
/////////////////////////////////////////////////////////////////////////////////////////////////


    val _suppliers = MutableStateFlow<List<Supplier>>(emptyList())

    fun loadSuppliers() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiClient.supplierApi.fetchSuppliers(FetchSuppliersRequest(userLogin))
                _suppliers.value = response
                error = false
            } catch (_: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _addSupplierSuccess = MutableStateFlow(false)

    fun addSupplier(
        name: String,
        phone_number: String,
        type: String,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                ApiClient.supplierApi.addSupplier(
                    AddSupplierRequest(
                        name = name,
                        phone_number = phone_number,
                        type = type,
                        user_login = userLogin
                    )
                )
                _addSupplierSuccess.value = true
                error = false
            } catch (e: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

val suppliersNames: StateFlow<Map<String, String>> = _suppliers
    .map { suppliers ->
        suppliers.associate { it.supplierId to it.name }
    }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

//Orders
/////////////////////////////////////////////////////////////////////////////////////////////////

    val _orders = MutableStateFlow<List<Order>>(emptyList())

    fun loadOrders() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiClient.orderApi.fetchOrders(FetchOrdersRequest(userLogin))
                _orders.value = response.sortedByDescending { it.number }
                error = false
            } catch (_: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _addOrderSuccess = MutableStateFlow(false)

    fun addOrder(
        amount: Int,
        delivery_date: String,
        status: String,
        product: String,
        price: Double,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                ApiClient.orderApi.addOrder(
                    AddOrderRequest(
                        amount = amount,
                        delivery_date = delivery_date,
                        status = status,
                        user_login = userLogin,
                        product = product,
                        price = price
                    )
                )
                _addOrderSuccess.value = true
                error = false
            } catch (e: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////

//Warehouses
/////////////////////////////////////////////////////////////////////////////////////////////////

    val _warehouses = MutableStateFlow<List<Warehouse>>(emptyList())

    fun loadWarehouses() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiClient.warehouseApi.fetchWarehouses(FetchWarehousesRequest(userLogin))
                _warehouses.value = response
                error = false
            } catch (_: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _addWarehouseSuccess = MutableStateFlow(false)

    fun addWarehouse(
        name: String,
        address: String,
        postal_address: String,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                ApiClient.warehouseApi.addWarehouse(
                    AddWarehouseRequest(
                        name = name,
                        address = address,
                        postal_address = postal_address,
                        user_login = userLogin
                    )
                )
                _addWarehouseSuccess.value = true
                error = false
            } catch (e: Exception) {
                error = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    val warehousesNames: StateFlow<Map<String, String>> = _warehouses
        .map { warehouses ->
            warehouses.associate { it.warehouseId to it.name }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

/////////////////////////////////////////////////////////////////////////////////////////////////

    fun onSearchTextChange(text: TextFieldValue) {
        _searchText.value = text

    }

    fun clearSearch() {
        _searchText.value = TextFieldValue("")
    }
}


//Products placeholder
//val testProduct = Product(
//    name = "Кофе Нескафе Gold растворимый 190г",
//    price = 649.99,
//    amount = 5,
//    expiryDate = "10.09.25",
//    amountSold = 123,
//    image = R.drawable.test
//)
//
//val testProduct1 = Product(
//    name = "Яйцо куриное Окское",
//    price = 119.99,
//    amount = 22,
//    expiryDate = "10.09.25",
//    amountSold = 43,
//    image = R.drawable.eggs
//)
//
//val testProduct2 = Product(
//    name = "Шампунь Шаума Men",
//    price = 249.99,
//    amount = 0,
//    expiryDate = "11.12.25",
//    amountSold = 123,
//    image = R.drawable.shampoo
//)
//
//private val allProducts = listOf(testProduct, testProduct1, testProduct2)


//Suppliers placeholder
//val supplier1 = Supplier(
//    name = "Ronald Martin",
//    phoneNumber = "7687764556",
//    type = "Принимает возврат",
//    suppliedProducts = listOf()
//)
//
//val supplier2 = Supplier(
//    name = "Tom Homan",
//    phoneNumber = "9867545368",
//    type = "Не принимает возврат",
//    suppliedProducts = listOf()
//)
//
//val supplier3 = Supplier(
//    name = "Fainden Juke",
//    phoneNumber = "9567545769",
//    type = "Принимает возврат",
//    suppliedProducts = listOf()
//)
//
//val supplier4 = Supplier(
//    name = "Dender Luke",
//    phoneNumber = "9567545769",
//    type = "Принимает возврат",
//    suppliedProducts = listOf()
//)
//
//private val allSuppliers = listOf(supplier1, supplier2, supplier3, supplier4)




//Orders placeholder
//val order1 = Order(
//    name = "№ 38762682-0172",
//    products = listOf(),
//    deliveryDate = "12.03.25",
//    status = OrderStatus.RECIEVED
//)
//
//val order2 = Order(
//    name = "№ 38762682-0173",
//    products = listOf(),
//    deliveryDate = "25.03.25",
//    status = OrderStatus.IN_TRANSIT
//)
//
//val order3 = Order(
//    name = "№ 38762682-0174",
//    products = listOf(),
//    deliveryDate = "19.04.25",
//    status = OrderStatus.CANCELED
//)

//val order4 = Order(
//    name = "№ 38762682-0175",
//    products = listOf(
//        testProduct,
//        testProduct1
//    ),
//    deliveryDate = "13.01.25",
//    status = OrderStatus.LATE
//)

//private val allOrders = listOf(order1, order2, order3)



//Warehouses placeholder
//val warehouse1 = Warehouse(
//    name = "Склад 1",
//    address = "г. Москва, ул. Покровка, 27, стр. 6",
//    postalCode = "044-653578",
//    color = Color(0xFF553BE5),
//)
//
//val warehouse2 = Warehouse(
//    name = "Склад 2",
//    address = "г. Москва, ул. Покровка, 27, стр. 6",
//    postalCode = "044-653578",
//    color = Color(0xFF22697B),
//)
//
//val warehouse3 = Warehouse(
//    name = "Склад 3",
//    address = "г. Москва, ул. Покровка, 27, стр. 6",
//    postalCode = "044-653578",
//    color = Color(0xFFEFDB69),
//)
//
//private val allWarehouses = listOf(warehouse1, warehouse2, warehouse3)