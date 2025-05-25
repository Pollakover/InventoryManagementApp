package com.example.inventorymanagementapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagementapp.database.warehouses.Warehouse
import com.example.inventorymanagementapp.screens.dialogs.NewWarehouseScreen
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun WarehousesScreen(viewModel: MainViewModel) {
    val warehouses by viewModel._warehouses.collectAsState()
    val userLogin = "test1"
    val clickOnAddButton = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadWarehouses(userLogin)
    }

    if (clickOnAddButton.value) {
        AddWarehouse(clickOnAddButton, viewModel)
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
                        modifier = Modifier.size(50.dp),
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
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(warehouses) { warehouse ->
                            WarehouseRow(warehouse)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddWarehouse(state: MutableState<Boolean>, viewModel: MainViewModel) {
    if(state.value) {
        Dialog(onDismissRequest = { state.value = false }) {
            NewWarehouseScreen(state, viewModel)
        }
    }
}

@Composable
fun WarehouseRow(warehouse: Warehouse) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, color = MaterialTheme.colorScheme.secondary, RoundedCornerShape(8.dp))
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(warehouse.name, style = CustomTextStyles.body1_medium, color = MaterialTheme.colorScheme.onTertiary)
            Text(warehouse.address, style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onPrimary)
            Text(warehouse.postal_address, style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWarehouses() {
    val mainViewModel = viewModel<MainViewModel>()
    WarehousesScreen(mainViewModel)
}