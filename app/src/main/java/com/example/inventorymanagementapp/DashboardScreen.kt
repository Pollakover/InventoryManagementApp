package com.example.inventorymanagementapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.*


@Composable
fun DashboardScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(22.dp),
        modifier = Modifier
            .fillMaxSize()
            //.background(color = MaterialTheme.colorScheme.primary)
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
                    color = gray_800,
                    style = CustomTextStyles.sub_heading_medium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(22.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    //Карточка
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(color = white).weight(1f)){
                        Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp )) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {

                                //Иконка
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(color = primary_100)
                                        .width(30.dp)
                                        .height(30.dp)){
                                    Icon(
                                        modifier = Modifier.align(Alignment.Center),
                                        painter = painterResource(id = R.drawable.container_icon),
                                        contentDescription = "",
                                        tint = primary_500,
                                    )
                                }

                                //Текст под иконкой
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)){
                                    Text("842", style = CustomTextStyles.body1_semi_bold, color = gray_600)
                                    Text("На складе", style = CustomTextStyles.body2_medium, color = gray_800)
                                }
                            }
                        }
                    }

                    //Карточка
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(color = white).weight(1f)){
                        Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp )) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {

                                //Иконка
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(color = success_100)
                                        .width(30.dp)
                                        .height(30.dp)){
                                    Icon(
                                        modifier = Modifier.align(Alignment.Center),
                                        painter = painterResource(id = R.drawable.checkmark_icon),
                                        contentDescription = "",
                                        tint = success_500,
                                    )
                                }

                                //Текст под иконкой
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)){
                                    Text("790", style = CustomTextStyles.body1_semi_bold, color = gray_600)
                                    Text("В наличии", style = CustomTextStyles.body2_medium, color = gray_800)
                                }
                            }
                        }
                    }
                }
            }

            //Заканчивается, нет в наличии
            Row(horizontalArrangement = Arrangement.spacedBy(22.dp), modifier = Modifier.fillMaxWidth()) {

                //Карточка
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(color = white).weight(1f)){
                    Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp )) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(color = warning_100)
                                    .width(30.dp)
                                    .height(30.dp)){
                                Icon(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.warning_icon),
                                    contentDescription = "",
                                    tint = warning_500,
                                )
                            }

                            //Текст под иконкой
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)){
                                Text("32", style = CustomTextStyles.body1_semi_bold, color = gray_600)
                                Text("Заканчивается", style = CustomTextStyles.body2_medium, color = gray_800)
                            }
                        }
                    }
                }

                //Карточка
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(color = white).weight(1f)){
                    Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp )) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {

                            //Иконка
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(color = error_100)
                                    .width(30.dp)
                                    .height(30.dp)){
                                Icon(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.error_icon),
                                    contentDescription = "",
                                    tint = error_500,
                                )
                            }

                            //Текст под иконкой
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)){
                                Text("46", style = CustomTextStyles.body1_semi_bold, color = gray_600)
                                Text("Нет в наличии", style = CustomTextStyles.body2_medium, color = gray_800)
                            }
                        }
                    }
                }
            }


            //Товары с низким запасом
            Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                Text("Товары с низким запасом", color = gray_800, style = CustomTextStyles.sub_heading_medium)
                Column(modifier = Modifier.fillMaxWidth().height(270.dp).clip(RoundedCornerShape(8.dp)).background(color = white)) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp),modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 4.dp).fillMaxSize()) {
                        items(6){
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp).fillMaxWidth()){
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.weight(4f)

                                ) {
                                    //Картинка продукта
                                    Row(
                                        modifier = Modifier.width(70.dp).height(70.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ){
                                        Image(
                                            painter = painterResource(R.drawable.test),
                                            modifier = Modifier.size(60.dp),
                                            contentDescription = "Logo"
                                        )
                                    }

                                    Column(
                                        modifier = Modifier,
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text("Название продукта", style = CustomTextStyles.body1_semi_bold, color = gray_800)
                                        Text("Осталось: 5 шт.", style = CustomTextStyles.body2_regular, color = gray_500)
                                    }
                                }
                                //Spacer(modifier = Modifier.weight(1f))
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                                    Badge(containerColor = warning_100){ Text("Мало", fontSize = 10.sp, color = warning_700, modifier = Modifier.padding(3.dp, 3.dp, 3.dp, 3.dp))}
                                }


                            }
                            //HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp))
                        }
                    }
                }
            }


            //Самые продаваемые товары
            Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                Text("Самые продаваемые товары", color = gray_800, style = CustomTextStyles.sub_heading_medium)
                Column(modifier = Modifier.fillMaxWidth().height(270.dp).clip(RoundedCornerShape(8.dp)).background(color = white)) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp),modifier = Modifier.padding(20.dp).fillMaxSize()) {
                        items(6){
                            Row(){
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.weight(1f)

                                ) {
                                    Text("Tata Salt", style = CustomTextStyles.body1_semi_bold, color = gray_800)
                                    Row(){
                                        Text("Продано: ", style = CustomTextStyles.body2_regular, color = gray_400)
                                        Text("100 шт.", style = CustomTextStyles.body2_regular, color = gray_600)
                                    }

                                }
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                    //modifier = Modifier.weight(1f)
                                ) {
                                    Row(){
                                        Text("Осталось: ", style = CustomTextStyles.body2_regular, color = gray_400)
                                        Text("5 шт.", style = CustomTextStyles.body2_regular, color = gray_600)
                                    }
                                    Row(){
                                        Text("Цена: ", style = CustomTextStyles.body2_regular, color = gray_400)
                                        Text("119.99 ₽", style = CustomTextStyles.body2_regular, color = gray_600)
                                    }
                                }
                            }
                            HorizontalDivider(thickness = 1.dp, color = gray_400, modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboard(){
    DashboardScreen()
}