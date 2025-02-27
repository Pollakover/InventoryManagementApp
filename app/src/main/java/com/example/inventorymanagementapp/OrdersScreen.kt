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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles

@Composable
fun OrdersScreen() {
    var search by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.gray_50)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.white))
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
                        containerColor = colorResource(R.color.primary_600),
                        contentColor = colorResource(R.color.white),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }

                    FloatingActionButton(
                        onClick = {  },
                        modifier = Modifier.border(1.dp, color = colorResource(R.color.gray_100), RoundedCornerShape(8.dp)).size(33.dp),
                        containerColor = colorResource(R.color.white),
                        contentColor = colorResource(R.color.gray_600),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.filters_icon), "Floating action button.")
                    }

                    BasicTextField(
                        value = search,
                        onValueChange = { search = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(33.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (search.text.isEmpty()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Поиск...",
                                        color = colorResource(R.color.gray_400),
                                        style = CustomTextStyles.body2_regular,
                                        fontSize = 12.sp
                                    )
                                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End){
                                        Icon(Icons.Filled.Search, "Floating action button.", tint = colorResource(R.color.gray_400))

                                    }
                                }

                            }
                            innerTextField()
                        }
                    )
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(10) {
                        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text("№ 38762682-0174", style = CustomTextStyles.body1_semi_bold, color = colorResource(R.color.gray_800))
                            Row {
                                Text("Продукт:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_600))
                                Text("Magi", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_400))
                            }
                            Row {
                                Text("Количество:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_600))
                                Text("43", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_400))
                            }
                            Row {
                                Text("Цена:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_600))
                                Text("2149.57 ₽", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_400))
                            }
                            Row {
                                Text("Ожидаемая дата доставки:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_600))
                                Text("12.03.25", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_400))
                            }
                            Row {
                                Text("Статус:", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = colorResource(R.color.gray_600))
                                Text("Получен", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = colorResource(R.color.success_600))
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp), color = colorResource(R.color.gray_50))
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