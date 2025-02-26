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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles

@Composable
fun InventoryScreen() {
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
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(10) {
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
                                        painter = painterResource(R.drawable.test),
                                        modifier = Modifier.size(80.dp),
                                        contentDescription = "Logo"
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Название продукта", style = CustomTextStyles.body1_semi_bold, color = colorResource(R.color.gray_800))
                                    Row {
                                        Text("Цена: ", style = CustomTextStyles.body2_regular, color = colorResource(R.color.gray_400))
                                        Text("119.99 ₽", style = CustomTextStyles.body2_regular, color = colorResource(R.color.gray_600))}
                                    Row {
                                        Text("Всего: ", style = CustomTextStyles.body2_regular, color = colorResource(R.color.gray_400))
                                        Text("43 пакета", style = CustomTextStyles.body2_regular, color = colorResource(R.color.gray_600))}
                                    Row {
                                        Text("Срок годности: ", style = CustomTextStyles.body2_regular, color = colorResource(R.color.gray_400))
                                        Text("11.12.25", style = CustomTextStyles.body2_regular, color = colorResource(R.color.gray_600))}
                                    Text("В наличии", style = CustomTextStyles.body2_regular, color = colorResource(R.color.success_500))
                                    //Text("Заканчивается", style = CustomTextStyles.body2_regular, color = colorResource(R.color.warning_500))
                                    //Text("Нет в наличии", style = CustomTextStyles.body2_regular, color = colorResource(R.color.error_500))
                                }
                            }
                            HorizontalDivider(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp), color = colorResource(R.color.gray_50))
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInventory() {
    InventoryScreen()
}