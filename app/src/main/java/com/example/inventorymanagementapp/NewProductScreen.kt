package com.example.inventorymanagementapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles

@Composable
fun NewProductScreen() {
    var productName by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(TextFieldValue("")) }



    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

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
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        //.border(1.dp, colorResource(R.color.gray_100), RoundedCornerShape(8.dp))
                        .drawBehind {
                            drawRoundRect(color = Color.Gray, style = stroke, cornerRadius = CornerRadius(10.dp.toPx()))
                        }
                )
                {
                    Icon(painter = painterResource(R.drawable.upload_file_icon), contentDescription = "Menu", tint = colorResource(R.color.gray_400), modifier = Modifier.size(80.dp))
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Название",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Введите название продукта",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Категория",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Выберите категорию",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Цена",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Введите цену",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Количество",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Введите количество",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Срок годности",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Введите срок годности",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Поставщик",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Выберите поставщика",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Склад",
                        color = colorResource(R.color.gray_700),
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = colorResource(R.color.gray_100),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = CustomTextStyles.body1_regular,
                        decorationBox = { innerTextField ->
                            if (productName.text.isEmpty()) {
                                Text(
                                    text = "Выберите склад",
                                    color = colorResource(R.color.gray_500),
                                    style = CustomTextStyles.body1_regular
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)){
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.white),
                            contentColor = colorResource(R.color.gray_500)
                        ),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Text("Отмена", style = CustomTextStyles.body2_medium)
                    }
                    Button(onClick = {  },
                            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.primary_600),
                                contentColor = colorResource(R.color.white)
                            ),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                        Text("Добавить продукт", style = CustomTextStyles.body2_medium)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNewProduct() {
    NewProductScreen()
}