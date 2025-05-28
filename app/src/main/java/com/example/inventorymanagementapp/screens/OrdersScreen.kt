package com.example.inventorymanagementapp.screens

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagementapp.NoResults
import com.example.inventorymanagementapp.QueryError
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.database.orders.Order
import com.example.inventorymanagementapp.screens.dialogs.NewOrderScreen
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun OrdersScreen(viewModel: MainViewModel) {
    val orders by viewModel._orders.collectAsState()
    val clickOnAddButton = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadOrders()
    }

    if (clickOnAddButton.value) {
        AddOrder(clickOnAddButton, viewModel)
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
                    if (orders.isEmpty()) {
                        EmptyOrdersList()
                    }
                    else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(orders) { order ->
                                OrderRow(order)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddOrder(state: MutableState<Boolean>, viewModel: MainViewModel) {
    if (state.value) {
        Dialog(onDismissRequest = { state.value = false }) {
            NewOrderScreen(state, viewModel)
        }
    }
}

@Composable
fun OrderRow(order: Order) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            "№ " + order.number,
            style = CustomTextStyles.body1_semi_bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row {
            Text(
                "Товар:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                order.product,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row {
            Text(
                "Количество:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                order.amount.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row {
            Text(
                "Цена:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                "${order.price} ₽",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row {
            Text(
                "Ожидаемая дата доставки:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                order.delivery_date,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row {
            Text(
                "Статус:",
                modifier = Modifier.weight(1f),
                style = CustomTextStyles.body2_medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                order.status,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = CustomTextStyles.body2_medium,
                color = setColor(order.status)
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
fun PreviewOrders() {
    val mainViewModel = viewModel<MainViewModel>()
    OrdersScreen(mainViewModel)
}

fun setColor(status: String): Color {
    return when (status) {
        "Получен" -> success_500
        "Доставлен" -> success_500
        "Задерживается" -> warning_500
        "Отменен" -> error_500
        else -> primary_500
    }
}

@Composable
fun EmptyOrdersList() {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.orders_icon),
            contentDescription = "Inventory icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(100.dp)
        )
        Text(
            "Вы пока не добавили заказы",
            color = MaterialTheme.colorScheme.onSecondary,
            style = CustomTextStyles.body2_regular,
            textAlign = TextAlign.Center
        )
    }
}