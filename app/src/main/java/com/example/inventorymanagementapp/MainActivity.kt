package com.example.inventorymanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanagementapp.screens.DashboardScreen
import com.example.inventorymanagementapp.screens.InventoryScreen
import com.example.inventorymanagementapp.screens.OrdersScreen
import com.example.inventorymanagementapp.screens.SuppliersScreen
import com.example.inventorymanagementapp.screens.WarehousesScreen
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles
import com.example.inventorymanagementapp.ui.theme.InventoryManagementAppTheme
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val mainViewModel = viewModel<MainViewModel>()
            InventoryManagementAppTheme(darkTheme = mainViewModel.isDarkModeOn) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                )
                {
                    //Demo_ExposedDropdownMenuBox()
                    MainScreen(mainViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val colors = arrayOf(MaterialTheme.colorScheme.onTertiary, MaterialTheme.colorScheme.onTertiary, MaterialTheme.colorScheme.onTertiary, MaterialTheme.colorScheme.onTertiary, MaterialTheme.colorScheme.onTertiary)
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.background)
                                .padding(24.dp)
                                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource(R.drawable.profile_placeholder),
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(50))
                                        .border(1.dp, color = MaterialTheme.colorScheme.primary, RoundedCornerShape(50)),
                                    contentDescription = "Logo"
                                )
                                Spacer(modifier = Modifier.weight(1f))

                                //Переключатель темы
                                IconButton(
                                    onClick = { mainViewModel.changeTheme() },
                                    modifier = Modifier.size(35.dp)
                                ) {
                                    Icon(
                                        if (mainViewModel.isDarkModeOn) painterResource(id = R.drawable.light_mode_icon) else painterResource(id = R.drawable.dark_mode_icon),
                                        contentDescription = "Light / Dark mode",
                                        tint = MaterialTheme.colorScheme.onTertiary
                                    )
                                }
                            }

                            //Имя и почта
                            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
                            {
                                Text(
                                    "Алексей Поляков",
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    style = CustomTextStyles.body1_bold
                                )
                                Text(
                                    "revokalloppollakover@gmail.com",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(24.dp, 0.dp, 24.dp, 0.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            TextButton(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(5.dp, 0.dp, 0.dp, 0.dp),
                                onClick = {
                                    navController.navigate("dashboard") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    if (currentRoute == "dashboard") {
                                        changeColors(colors, 0)
                                    }

                                    Icon(
                                        painter = painterResource(id = R.drawable.home_icon),
                                        contentDescription = "",
                                        tint = colors[0],
                                    )

                                    Text(
                                        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
                                        text = "Приборная панель",
                                        style = CustomTextStyles.body1_medium,
                                        color = colors[0]
                                    )
                                }
                            }

                            TextButton(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(5.dp, 0.dp, 0.dp, 0.dp),
                                onClick = {
                                    navController.navigate("inventory") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    if (currentRoute == "inventory") {
                                        changeColors(colors, 1)
                                    }

                                    Icon(
                                        painter = painterResource(id = R.drawable.inventory_icon),
                                        contentDescription = "",
                                        tint = colors[1],
                                    )

                                    Text(
                                        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
                                        text = "Инвентарь",
                                        style = CustomTextStyles.body1_medium,
                                        color = colors[1]
                                    )
                                }
                            }

                            TextButton(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(5.dp, 0.dp, 0.dp, 0.dp),
                                onClick = {
                                    navController.navigate("suppliers") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    if (currentRoute == "suppliers") {
                                        changeColors(colors, 2)
                                    }

                                    Icon(
                                        painter = painterResource(id = R.drawable.suppliers_icon),
                                        contentDescription = "",
                                        tint = colors[2],
                                    )

                                    Text(
                                        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
                                        text = "Поставщики",
                                        style = CustomTextStyles.body1_medium,
                                        color = colors[2]
                                    )
                                }
                            }

                            TextButton(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(5.dp, 0.dp, 0.dp, 0.dp),
                                onClick = {
                                    navController.navigate("orders") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    if (currentRoute == "orders") {
                                        changeColors(colors, 3)
                                    }

                                    Icon(
                                        painter = painterResource(id = R.drawable.orders_icon),
                                        contentDescription = "",
                                        tint = colors[3],
                                    )

                                    Text(
                                        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
                                        text = "Заказы",
                                        style = CustomTextStyles.body1_medium,
                                        color = colors[3]
                                    )
                                }
                            }

                            TextButton(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(5.dp, 0.dp, 0.dp, 0.dp),
                                onClick = {
                                    navController.navigate("warehouses") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    if (currentRoute == "warehouses") {
                                        changeColors(colors, 4)
                                    }

                                    Icon(
                                        painter = painterResource(id = R.drawable.warehouses_icon),
                                        contentDescription = "",
                                        tint = colors[4],
                                    )
                                    Text(
                                        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
                                        text = "Склады",
                                        style = CustomTextStyles.body1_medium,
                                        color = colors[4]
                                    )
                                }
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    val logoutDialogState = remember { mutableStateOf(false) }

                    TextButton(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 0.dp, 24.dp, 32.dp),
                        contentPadding = PaddingValues(5.dp, 0.dp, 0.dp, 0.dp),
                        onClick = { logoutDialogState.value = true }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            if (logoutDialogState.value) {
                                ExitDialog(logoutDialogState)
                            }

                            Icon(
                                painter = painterResource(id = R.drawable.logout_icon),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onTertiary,
                            )
                            Text(
                                modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
                                text = "Выйти",
                                style = CustomTextStyles.body1_medium,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    }
                }
            }
        },
    ) {
        if (drawerState.currentValue == DrawerValue.Open) {
            if (mainViewModel.searchButtonState) {
                mainViewModel.changeButtonState()
            }
        }
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    title = {
                        val topBarText = when (currentRoute) {
                            "dashboard" -> "Панель управления"
                            "inventory" -> "Инвентарь"
                            "suppliers" -> "Поставщики"
                            "orders" -> "Заказы"
                            "warehouses" -> "Склады"
                            else -> ""
                        }
                        if (mainViewModel.searchButtonState) {
                            TopSearchBar(mainViewModel)
                        } else {
                            Text(
                                text = topBarText,
                                style = CustomTextStyles.sub_heading_medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                            if (mainViewModel.searchButtonState) {
                                mainViewModel.changeButtonState()
                            }
                        }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.onSurface)
                        }
                    },
                    actions = {
                        if (currentRoute != "dashboard" && currentRoute != "warehouses") {
                            IconButton(
                                onClick = { mainViewModel.changeButtonState() }
                            ) {
                                Icon(
                                    imageVector = if (mainViewModel.searchButtonState) Icons.Filled.Close else Icons.Filled.Search,
                                    contentDescription = "Search",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    },
                )
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("black") { Demo_ExposedDropdownMenuBox() }
                composable("dashboard") { DashboardScreen() }
                composable("inventory") { InventoryScreen(mainViewModel) }
                composable("suppliers") { SuppliersScreen(mainViewModel) }
                composable("orders") { OrdersScreen(mainViewModel) }
                composable("warehouses") { WarehousesScreen(mainViewModel) }
            }
        }
    }
}

@Composable
fun ExitDialog(state: MutableState<Boolean>) {
    if (state.value) {
        Dialog(onDismissRequest = { state.value = false }) {
            Card(
                modifier = Modifier
                    .width(320.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout_icon),
                            contentDescription = "",
                            tint = warning_500,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(
                            "Вы уверены, что хотите выйти из своей учетной записи?",
                            modifier = Modifier.fillMaxWidth(),
                            style = CustomTextStyles.body2_regular,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            TextButton(
                                onClick = { state.value = false },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = primary_500,
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            ) {
                                Text("Да")
                            }

                            TextButton(
                                onClick = { state.value = false },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = primary_500,
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            ) {
                                Text("Нет")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopSearchBar(viewModel: MainViewModel) {
    val searchText by viewModel.searchText.collectAsState()
    val isInitialized = remember { mutableStateOf(false) }
    val rainbowColors: List<Color> = listOf(primary_500, success_500)
    val focusManager = LocalFocusManager.current
    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }

    LaunchedEffect(isInitialized) {
        if (!isInitialized.value) {
            viewModel.requestSearchFocus()
            isInitialized.value = true
        }
    }

    TextField(
        singleLine = true,
        value = searchText,
        textStyle = TextStyle(
            brush = brush,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
        ),
        trailingIcon = {
            if (searchText.text.isNotBlank()) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Очистить поиск",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable {
                        viewModel.clearSearch()
                        focusManager.clearFocus()
                        //viewModel.clearSearchFocus()
                    }
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = transparent, // Фон при фокусе
            unfocusedContainerColor = transparent,
            disabledContainerColor = transparent,
            focusedTextColor = MaterialTheme.colorScheme.onSurface, // Цвет текста
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface, // Цвет текста
            cursorColor = MaterialTheme.colorScheme.onSurface, // Цвет курсора
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            disabledIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        onValueChange = viewModel::onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(viewModel.focusRequester),
        placeholder = {
            Text(
                "Поиск…",
                style = CustomTextStyles.body2_regular,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    val mainViewModel = viewModel<MainViewModel>()
    MainScreen(mainViewModel)
}

fun changeColors(array: Array<Color>, index: Int) {
    array[index] = primary_500
}
