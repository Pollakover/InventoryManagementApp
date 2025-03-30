package com.example.inventorymanagementapp.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.ui.theme.*

@Composable
fun ClickOnItemScreen() {

    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

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
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                val boxColor = MaterialTheme.colorScheme.secondary
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .drawBehind {
                            drawRoundRect(color = boxColor, style = stroke, cornerRadius = CornerRadius(10.dp.toPx()))
                        }
                )
                {
                    Image(
                        painter = painterResource(R.drawable.test),
                        modifier = Modifier.size(80.dp),
                        contentDescription = "Logo"
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp), color = MaterialTheme.colorScheme.background)

                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Основная информация", style = CustomTextStyles.body1_semi_bold, color = MaterialTheme.colorScheme.onBackground)
                        Row {
                            Text("Название", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                            Text("Название продукта", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                        }
                        Row {
                            Text("ID продукта", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                            Text("8245930", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                        }
                        Row {
                            Text("Категория продукта", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                            Text("Бытовая химия", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                        }
                        Row {
                            Text("Срок годности", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                            Text("11.12.25", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                        }
                        Row {
                            Text("Цена", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                            Text("119.99 ₽", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                        }
                    }

                HorizontalDivider(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp), color = MaterialTheme.colorScheme.background)

                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Информация о поставщике", style = CustomTextStyles.body1_semi_bold, color = MaterialTheme.colorScheme.onBackground)
                    Row {
                        Text("Имя поставщика", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                        Text("Ronald Martin", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                    }
                    Row {
                        Text("Номер телефона", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                        Text("98789 86757", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onTertiary)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp), color = MaterialTheme.colorScheme.background)

                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Запасы на складах", style = CustomTextStyles.body1_semi_bold, color = MaterialTheme.colorScheme.onBackground)
                    Column {
                        Row(
                            modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(color = MaterialTheme.colorScheme.background).padding(12.dp)
                        ){
                            Text("Склад", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_semi_bold, color = MaterialTheme.colorScheme.onSecondary)
                            Text("Кол-во на складе", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_semi_bold, color = MaterialTheme.colorScheme.onSecondary)
                        }
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(4){
                                Column {
                                    Row(modifier = Modifier.padding(12.dp)){
                                        Text("Sulur Branch", modifier = Modifier.weight(1f), style = CustomTextStyles.body2_medium, color = MaterialTheme.colorScheme.onPrimary)
                                        Text("24", modifier = Modifier.weight(1f), textAlign = TextAlign.End, style = CustomTextStyles.body2_medium, color = primary_500)
                                    }
                                }
                                HorizontalDivider(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp), color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }


                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewClickOnItem() {
    ClickOnItemScreen()
}