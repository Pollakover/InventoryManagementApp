package com.example.inventorymanagementapp.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.database.suppliers.Supplier
import com.example.inventorymanagementapp.screens.dialogs.NewSupplierScreen
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun SuppliersScreen(viewModel: MainViewModel) {
    val suppliers by viewModel._suppliers.collectAsState()
    val clickOnAddButton = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadSuppliers()
    }

    if (clickOnAddButton.value) {
        AddSupplier(clickOnAddButton, viewModel)
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
                    if (suppliers.isEmpty()) {
                        EmptySuppliersList()
                    }
                    else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(suppliers) { supplier ->
                                SupplierRow(supplier)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun SupplierRow(supplier: Supplier) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            supplier.name,
            style = CustomTextStyles.body1_semi_bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row {
            Text(
                "Номер телефона:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                supplier.phone_number,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row {
            Text(
                "Тип:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                supplier.type,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = if (supplier.type == "Принимает возврат") success_500 else error_500
            )
        }
        Row {
            Text(
                "Поставляемые товары:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                if (supplier.allProducts().isEmpty()) "Нет" else (supplier.allProducts()),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
        color = MaterialTheme.colorScheme.background
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewSuppliers() {
    val mainViewModel = viewModel<MainViewModel>()
    SuppliersScreen(mainViewModel)
}

@Composable
fun AddSupplier(state: MutableState<Boolean>, viewModel: MainViewModel) {
    if (state.value) {
        Dialog(onDismissRequest = { state.value = false }) {
            NewSupplierScreen(state, viewModel)
        }
    }
}

@Composable
fun EmptySuppliersList() {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.suppliers_icon),
            contentDescription = "Inventory icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(100.dp)
        )
        Text(
            "Вы пока не добавили поставщиков",
            color = MaterialTheme.colorScheme.onSecondary,
            style = CustomTextStyles.body2_regular,
            textAlign = TextAlign.Center
        )
    }
}