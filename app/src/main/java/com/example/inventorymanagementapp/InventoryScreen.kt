package com.example.inventorymanagementapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import com.example.inventorymanagementapp.data.models.Product
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun InventoryScreen(viewModel: MainViewModel) {
    val products by viewModel.products.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = gray_50
            )
    ) {
        Column(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(color = white)
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
                        containerColor = primary_600,
                        contentColor = white,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }

                    FloatingActionButton(
                        onClick = {  },
                        modifier = Modifier.border(1.dp, color = gray_100, RoundedCornerShape(8.dp)).size(33.dp),
                        containerColor = white,
                        contentColor = gray_600,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.filters_icon), "Floating action button.")
                    }
                }

                if(products.isEmpty()){
                    NoResults()
//                    QueryError()
                }
                else{
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
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
    Column(){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier.width(100.dp).height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(product.image),
                    modifier = Modifier.size(80.dp),
                    contentDescription = "Logo"
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(product.name, style = CustomTextStyles.body1_semi_bold, color = gray_800)
                Row {
                    Text("Цена: ", style = CustomTextStyles.body2_regular, color = gray_400)
                    Text("${product.price} ₽", style = CustomTextStyles.body2_regular, color = gray_600)
                }
                Row {
                    Text("Всего на складах: ", style = CustomTextStyles.body2_regular, color = gray_400)
                    Text("${product.amount}", style = CustomTextStyles.body2_regular, color = gray_600)
                }
                Row {
                    Text("Срок годности: ", style = CustomTextStyles.body2_regular, color = gray_400)
                    Text(product.expiryDate, style = CustomTextStyles.body2_regular, color = gray_600)
                }
                if (product.amount == 0) Text("Нет в наличии", style = CustomTextStyles.body2_regular, color = error_500)
                else
                    if (product.amount <= 10) Text("Заканчивается", style = CustomTextStyles.body2_regular, color = warning_500)
                    else Text("В наличии", style = CustomTextStyles.body2_regular, color = success_500)
                }
            }
        }
        HorizontalDivider(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp), color = gray_50)
}

@Preview(showBackground = true)
@Composable
fun PreviewInventory() {
    val mainViewModel = viewModel<MainViewModel>()
    InventoryScreen(mainViewModel)
}