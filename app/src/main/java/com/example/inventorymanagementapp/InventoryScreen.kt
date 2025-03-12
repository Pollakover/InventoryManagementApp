package com.example.inventorymanagementapp

import android.graphics.Bitmap
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.data.models.Product
import com.example.inventorymanagementapp.ui.theme.*
import java.sql.Date

@Composable
fun InventoryScreen() {
    var search by remember { mutableStateOf(TextFieldValue("")) }

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
                verticalArrangement = Arrangement.spacedBy(10.dp),
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
/*
                    BasicTextField(
                        value = search,
                        onValueChange = { search = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(33.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = gray_100,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (search.text.isEmpty()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Поиск...",
                                        color = gray_400,
                                        style = CustomTextStyles.body2_regular,
                                        fontSize = 12.sp
                                    )
                                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End){
                                        Icon(Icons.Filled.Search, "Floating action button.", tint = gray_400)

                                    }
                                }

                            }
                            innerTextField()
                        }
                    )*/
                }
                val productsList = listOf(testProduct, testProduct1, testProduct2)

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(productsList) { product ->
                        ProductRow(product)

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
    image = R.drawable.icon
)

val testProduct2 = Product(
    name = "Шампунь Шаума Men",
    price = 249.99,
    amount = 0,
    expiryDate = "11.12.25",
    amountSold = 123,
    image = R.drawable.profile_placeholder
)


@Preview(showBackground = true)
@Composable
fun PreviewInventory() {
    InventoryScreen()
}