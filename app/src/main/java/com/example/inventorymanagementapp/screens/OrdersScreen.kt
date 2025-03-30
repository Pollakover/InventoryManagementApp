package com.example.inventorymanagementapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagementapp.NoResults
import com.example.inventorymanagementapp.QueryError
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.data.models.Order
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun OrdersScreen(viewModel: MainViewModel) {
    val orders by viewModel.orders.collectAsState()

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
                        onClick = {  },
                        modifier = Modifier.size(33.dp),
                        containerColor = primary_500,
                        contentColor = white,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }

                    FloatingActionButton(
                        onClick = {  },
                        modifier = Modifier
                            .border(1.dp, color = MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                            .size(33.dp),
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onTertiary,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.filters_icon), "Floating action button.")
                    }
                }

                if(viewModel.error) {
                    QueryError(viewModel)
                }
                else {
                    if(orders.isEmpty()){
                        NoResults()
                    }
                    else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
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
fun OrderRow (order : Order) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(order.name, style = CustomTextStyles.body1_semi_bold, color = MaterialTheme.colorScheme.onSurface)
        Row {
            Text("Продукты:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
            Text(order.allProducts(), modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
        }
        Row {
            Text("Количество:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
            Text(order.calculateAmount(), modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
        }
        Row {
            Text("Цена:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
            Text("${order.calculatePrice()} ₽", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
        }
        Row {
            Text("Ожидаемая дата доставки:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
            Text(order.deliveryDate, modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
        }
        Row {
            Text("Статус:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
            Text(order.status.text, modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = order.status.color)
        }
    }
    HorizontalDivider(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp), color = MaterialTheme.colorScheme.background)
}


@Preview(showBackground = true)
@Composable
fun PreviewOrders() {
    val mainViewModel = viewModel<MainViewModel>()
    OrdersScreen(mainViewModel)
}