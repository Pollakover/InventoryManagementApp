package com.example.inventorymanagementapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.database.products.Product
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel


@Composable
fun DashboardScreen(viewModel: MainViewModel) {

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
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
        val lowStockProducts = viewModel.getLowStockProducts()
        val highDemandProducts = viewModel.getHighDemandProducts()

        Column(
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .padding(22.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                //Товар на складах
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Товар на складах",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = CustomTextStyles.sub_heading_medium
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(22.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        //Карточка
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = MaterialTheme.colorScheme.surface)
                                .weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {

                                    //Иконка
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(color = primary_100)
                                            .width(30.dp)
                                            .height(30.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier.align(Alignment.Center),
                                            painter = painterResource(id = R.drawable.container_icon),
                                            contentDescription = "",
                                            tint = primary_500,
                                        )
                                    }

                                    //Текст под иконкой
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Text(
                                            "${viewModel.countProducts()}",
                                            style = CustomTextStyles.body1_semi_bold,
                                            color = MaterialTheme.colorScheme.onTertiary
                                        )
                                        Text(
                                            "На складе",
                                            style = CustomTextStyles.body2_medium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }

                        //Карточка
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = MaterialTheme.colorScheme.surface)
                                .weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {

                                    //Иконка
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(color = success_100)
                                            .width(30.dp)
                                            .height(30.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier.align(Alignment.Center),
                                            painter = painterResource(id = R.drawable.checkmark_icon),
                                            contentDescription = "",
                                            tint = success_500,
                                        )
                                    }

                                    //Текст под иконкой
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Text(
                                            "${viewModel.countProductsInStock()}",
                                            style = CustomTextStyles.body1_semi_bold,
                                            color = MaterialTheme.colorScheme.onTertiary
                                        )
                                        Text(
                                            "В наличии",
                                            style = CustomTextStyles.body2_medium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                //Заканчивается, нет в наличии
                Row(
                    horizontalArrangement = Arrangement.spacedBy(22.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    //Карточка
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = MaterialTheme.colorScheme.surface)
                            .weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(color = warning_100)
                                        .width(30.dp)
                                        .height(30.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.align(Alignment.Center),
                                        painter = painterResource(id = R.drawable.warning_icon),
                                        contentDescription = "",
                                        tint = warning_500,
                                    )
                                }

                                //Текст под иконкой
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Text(
                                        "${viewModel.countProductsLowInStock()}",
                                        style = CustomTextStyles.body1_semi_bold,
                                        color = MaterialTheme.colorScheme.onTertiary
                                    )
                                    Text(
                                        "Заканчивается",
                                        style = CustomTextStyles.body2_medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    //Карточка
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = MaterialTheme.colorScheme.surface)
                            .weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                //Иконка
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(color = error_100)
                                        .width(30.dp)
                                        .height(30.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.align(Alignment.Center),
                                        painter = painterResource(id = R.drawable.error_icon),
                                        contentDescription = "",
                                        tint = error_500,
                                    )
                                }

                                //Текст под иконкой
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Text(
                                        "${viewModel.countProductsNotInStock()}",
                                        style = CustomTextStyles.body1_semi_bold,
                                        color = MaterialTheme.colorScheme.onTertiary
                                    )
                                    Text(
                                        "Нет в наличии",
                                        style = CustomTextStyles.body2_medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }


                //Товары с низким запасом
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Товары с низким запасом",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = CustomTextStyles.sub_heading_medium
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 300.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = MaterialTheme.colorScheme.surface),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (lowStockProducts.isEmpty()) {
                            Text(
                                "Список товаров пуст",
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = CustomTextStyles.body2_regular,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .padding(0.dp, 10.dp, 5.dp, 10.dp)
                                    .fillMaxWidth()
                            ) {
                                items(lowStockProducts) { product ->
                                    LowStockColumnRow(product)
                                }
                            }
                        }

                    }
                }


                //Самые продаваемые товары
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Самые продаваемые товары",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = CustomTextStyles.sub_heading_medium
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 300.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = MaterialTheme.colorScheme.surface),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (highDemandProducts.isEmpty()){
                            Text(
                                "Список товаров пуст или нет продаж",
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = CustomTextStyles.body2_regular,
                                textAlign = TextAlign.Center
                            )
                        }
                        else {
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            ) {
                                items(highDemandProducts) { product ->
                                    HighDemandColumnRow(product)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun LowStockColumnRow(product: Product) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp, 0.dp, 10.dp, 0.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(4f)

        ) {
            //Картинка продукта
            Row(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = product.image_data,
                    contentDescription = "Product image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.icon),
                    error = painterResource(R.drawable.icon)
                )
            }

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    product.name,
                    style = CustomTextStyles.body1_semi_bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    "Осталось: ${product.amount} шт.",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            if (product.amount >= 10) {
                Badge(containerColor = success_100) {
                    Text(
                        "Много",
                        fontSize = 10.sp,
                        color = success_700,
                        modifier = Modifier.padding(3.dp, 3.dp, 3.dp, 3.dp)
                    )
                }
            } else {
                if (product.amount == 0) {
                    Badge(containerColor = error_100) {
                        Text(
                            "Нет",
                            fontSize = 10.sp,
                            color = error_700,
                            modifier = Modifier.padding(3.dp, 3.dp, 3.dp, 3.dp)
                        )
                    }
                } else {
                    Badge(containerColor = warning_100) {
                        Text(
                            "Мало",
                            fontSize = 10.sp,
                            color = warning_700,
                            modifier = Modifier.padding(3.dp, 3.dp, 3.dp, 3.dp)
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun HighDemandColumnRow(product: Product) {
    Row {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)

        ) {
            Text(
                product.name,
                style = CustomTextStyles.body1_semi_bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row {
                Text(
                    "Продано: ",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    "${product.amount_sold} шт.",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row {
                Text(
                    "Осталось: ",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    "${product.amount} шт.",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
            Row {
                Text(
                    "Цена: ",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    "${product.price} ₽",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)
    )
}