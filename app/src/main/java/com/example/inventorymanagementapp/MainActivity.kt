package com.example.inventorymanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanagementapp.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            InventoryManagementAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                )
                {
                    //Demo_ExposedDropdownMenuBox()
                    AppScaffold()
                }

                //Demo_ExposedDropdownMenuBox()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var colors = arrayOf(gray_600, gray_600, gray_600, gray_600, gray_600)
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val openDialog = remember { mutableStateOf(true) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = white)) {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Column(modifier = Modifier.background(color = gray_50).padding(24.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Image(
                                painter = painterResource(R.drawable.profile_placeholder),
                                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(50)).border(1.dp, color = gray_100, RoundedCornerShape(50)),
                                contentDescription = "Logo"
                            )

                            //Имя и почта
                            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
                            {
                                Text("Алексей Поляков", color = gray_600, style = CustomTextStyles.body1_bold)
                                Text("revokalloppollakover@gmail.com", color = gray_400, fontSize = 12.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.5.sp)
                            }
                        }
                        Column(modifier = Modifier.padding(24.dp, 0.dp, 24.dp, 0.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    var state = true
                                    navController.navigate("dashboard") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                if(currentRoute == "dashboard") {
                                    changeColors(colors, 0)
                                }

                                Icon(
                                    painter = painterResource(id = R.drawable.home_icon),
                                    contentDescription = "",
                                    tint = colors[0],
                                )

                                Text(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),text="Приборная панель", style = CustomTextStyles.body1_medium, color = colors[0])
                            }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    var state = false

                                    navController.navigate("inventory") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                if(currentRoute == "inventory") {
                                    changeColors(colors, 1)
                                }

                                Icon(
                                    painter = painterResource(id = R.drawable.inventory_icon),
                                    contentDescription = "",
                                    tint = colors[1],
                                )

                                Text(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), text="Инвентарь", style = CustomTextStyles.body1_medium, color = colors[1])
                            }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("suppliers") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                if(currentRoute == "suppliers") {
                                    changeColors(colors, 2)
                                }

                                Icon(
                                    painter = painterResource(id = R.drawable.suppliers_icon),
                                    contentDescription = "",
                                    tint = colors[2],
                                )

                                Text(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), text="Поставщики", style = CustomTextStyles.body1_medium, color = colors[2])
                            }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    var state = false
                                    navController.navigate("orders") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                    verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                if(currentRoute == "orders") {
                                    changeColors(colors, 3)
                                }

                                Icon(
                                    painter = painterResource(id = R.drawable.orders_icon),
                                    contentDescription = "",
                                    tint = colors[3],
                                )

                                Text(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), text="Заказы", style = CustomTextStyles.body1_medium, color = colors[3])
                            }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("warehouses") {
                                        //test = navController.graph.route.toString()
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                if(currentRoute == "warehouses") {
                                    changeColors(colors, 4)
                                }

                                Icon(
                                    painter = painterResource(id = R.drawable.warehouses_icon),
                                    contentDescription = "",
                                    tint = colors[4],
                                )
                                Text(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), text="Склады", style = CustomTextStyles.body1_medium, color = colors[4])
                            }

                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(modifier = Modifier.padding(24.dp, 0.dp, 24.dp, 32.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {


                        val logoutDialogState = remember { mutableStateOf(false) }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {logoutDialogState.value = true},
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ){

                            if (logoutDialogState.value){
                                ExitDialog(logoutDialogState)
                            }

                            Icon(
                                painter = painterResource(id = R.drawable.logout_icon),
                                contentDescription = "",
                                tint = gray_600,
                            )

                            if (currentRoute != null) {
                                Text(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), text="Выйти", style = CustomTextStyles.body1_medium, color = gray_600)
                            }
                        }
                    }
                }
            }
        },
    ) {
        Scaffold(
            containerColor = gray_50,
            topBar = {
                val searchButton  = remember { mutableStateOf(false) }
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = white,
                    ),
                    title = {
                        var topBarText = when (currentRoute){
                            "dashboard" -> "Панель управления"
                            "inventory" -> "Инвентарь"
                            "suppliers" -> "Поставщики"
                            "orders" -> "Заказы"
                            "warehouses" -> "Склады"
                            else -> ""
                        }
                        if(searchButton.value) {
                            TestSearchBar()
                        }
                        else{
                            Text(
                                text = topBarText,
                                style = CustomTextStyles.sub_heading_medium,
                                color = gray_800
                            )
                        }




                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = gray_800)
                        }
                    },
                    actions = {
                        if(currentRoute != "dashboard" && currentRoute != "warehouses"){
                            IconButton(onClick = { searchButton.value = !searchButton.value }) {
                                Icon(
                                    imageVector = if (searchButton.value) Icons.Filled.Close else Icons.Filled.Search,
                                    contentDescription = "Search",
                                    tint = gray_800
                                )
                            }
                        }
                    },
                )
            },
        ) {
            innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(innerPadding)
            ){
                composable("test") { Demo_ExposedDropdownMenuBox() }
                composable("dashboard") { DashboardScreen() }
                composable("inventory") { InventoryScreen() }
                composable("suppliers") { SuppliersScreen() }
                composable("orders") { OrdersScreen() }
                composable("warehouses") { WarehousesScreen() }
            }
        }


    }
}

@Composable
fun ExitDialog(state : MutableState<Boolean>) {
    //val openDialog = remember { mutableStateOf(state.value) }
    if(state.value){
            Dialog(onDismissRequest = { state.value = false }) {
                Card(
                    modifier = Modifier
                        .width(320.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = white
                    ),
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(20.dp) , verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout_icon),
                                contentDescription = "",
                                tint = warning_500,
                            modifier = Modifier.size(30.dp)
                            )
                            Text("Вы уверены, что хотите выйти из своей учетной записи?", modifier = Modifier.fillMaxWidth(), style = CustomTextStyles.body2_regular, color = gray_800)
                        }
                        Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

                            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                                TextButton(
                                    onClick = {state.value = false},
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = primary_500,
                                        containerColor = primary_50
                                    )
                                ) {
                                    Text("Да")
                                }

                                TextButton(
                                    onClick = {state.value = false},
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = primary_500,
                                        containerColor = primary_50
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

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    AppScaffold()
    val openDialog = remember { mutableStateOf(true) }
    //ExitDialog(openDialog)

}

fun changeColors(array : Array<Color>, index: Int){
    array[index] = primary_500
}
