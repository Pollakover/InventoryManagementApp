package com.example.inventorymanagementapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.database.products.Product
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.inventorymanagementapp.screens.dialogs.NewProductScreen

@Composable
fun InventoryScreen(viewModel: MainViewModel) {

    val products by viewModel._products.collectAsState()
    val userLogin = "test1"
    val clickOnAddButton = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadProducts(userLogin)
    }

    if (clickOnAddButton.value) {
        ClickOnAddButton(clickOnAddButton, viewModel)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        Column(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    FloatingActionButton(
                        onClick = { clickOnAddButton.value = true },
                        modifier = Modifier.size(33.dp),
                        containerColor = primary_500,
                        contentColor = white,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }

                val isLoading by viewModel.isLoading.collectAsState()

                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else if (viewModel.error) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Ошибка загрузки", color = MaterialTheme.colorScheme.error)
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(products) { product ->
                            ProductRow(product)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductRow(product: Product) {
    val clickOnItemDialogState = remember { mutableStateOf(false) }
    Column(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { clickOnItemDialogState.value = true }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier
                .fillMaxWidth()

        ) {

            if (clickOnItemDialogState.value) {
                ClickOnItem(product, clickOnItemDialogState)
            }

            Row(
                modifier = Modifier.width(100.dp).height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                AsyncImage(
                    model = product.image_data,
                    contentDescription = "Product image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.icon), // необязательный плейсхолдер
                    error = painterResource(R.drawable.icon) // необязательная ошибка загрузки
                )

            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(product.name, style = CustomTextStyles.body1_semi_bold, color = MaterialTheme.colorScheme.onSurface)
                Row {
                    Text("Цена: ", style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onPrimary)
                    Text("${product.price} ₽", style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onTertiary)
                }
                Row {
                    Text("Всего на складах: ", style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onPrimary)
                    Text("${product.amount}", style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onTertiary)
                }
                if (product.amount == 0) Text("Нет в наличии", style = CustomTextStyles.body2_regular, color = error_500)
                else
                    if (product.amount <= 10) Text("Заканчивается", style = CustomTextStyles.body2_regular, color = warning_500)
                    else Text("В наличии", style = CustomTextStyles.body2_regular, color = success_500)
                }
            }
        }
        HorizontalDivider(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp), color = MaterialTheme.colorScheme.background)
}

@Preview(showBackground = true)
@Composable
fun PreviewInventory() {
    val mainViewModel = viewModel<MainViewModel>()
    InventoryScreen(mainViewModel)
}

@Composable
fun ClickOnItem(product: Product, state: MutableState<Boolean>) {
    if(state.value) {
        Dialog(onDismissRequest = { state.value = false }) {
            ClickOnItemScreen(product)
        }
    }
}

@Composable
fun ClickOnAddButton(state: MutableState<Boolean>, viewModel: MainViewModel) {
    if(state.value) {
        Dialog(onDismissRequest = { state.value = false }) {
            NewProductScreen(state, viewModel)
        }
    }
}