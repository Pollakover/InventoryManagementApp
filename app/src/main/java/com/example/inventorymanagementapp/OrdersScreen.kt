package com.example.inventorymanagementapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventorymanagementapp.ui.theme.*

@Composable
fun OrdersScreen() {
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
                        .height(55.dp)
                ) {

                    FloatingActionButton(
                        onClick = {  },
                        modifier = Modifier.size(51.dp),
                        containerColor = primary_600,
                        contentColor = white,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }

                    FloatingActionButton(
                        onClick = {  },
                        modifier = Modifier.border(1.dp, color = gray_100, RoundedCornerShape(8.dp)).size(51.dp),
                        containerColor = white,
                        contentColor = gray_600,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.filters_icon), "Floating action button.")
                    }

                    var text1 by remember { mutableStateOf("") }

                    TextField(
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(vertical = 4.dp)
                            .border(1.dp, color = gray_100, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        leadingIcon = {
                            Icon(Icons.Filled.Search, "Floating action button.", tint = gray_400)
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = gray_50, // Фон при фокусе
                            unfocusedContainerColor = white,
                            disabledContainerColor = white,
                            focusedTextColor = gray_800, // Цвет текста
                            unfocusedTextColor = gray_800, // Цвет текста
                            cursorColor = gray_800, // Цвет курсора
                            focusedIndicatorColor = transparent,
                            unfocusedIndicatorColor = transparent,
                            disabledIndicatorColor = transparent
                        ),
                        textStyle = CustomTextStyles.body2_regular,
                        placeholder = {
                            Text("Поиск…", style = CustomTextStyles.body2_regular, color = gray_400, modifier = Modifier.fillMaxSize())
                        },
                    )
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(10) {
                        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text("№ 38762682-0174", style = CustomTextStyles.body1_semi_bold, color = gray_800)
                            Row {
                                Text("Продукт:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = gray_600)
                                Text("Magi", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = gray_400)
                            }
                            Row {
                                Text("Количество:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = gray_600)
                                Text("43", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = gray_400)
                            }
                            Row {
                                Text("Цена:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = gray_600)
                                Text("2149.57 ₽", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = gray_400)
                            }
                            Row {
                                Text("Ожидаемая дата доставки:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = gray_600)
                                Text("12.03.25", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = gray_400)
                            }
                            Row {
                                Text("Статус:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = gray_600)
                                Text("Получен", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = success_600)
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp), color = gray_50)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOrders() {
    OrdersScreen()
}