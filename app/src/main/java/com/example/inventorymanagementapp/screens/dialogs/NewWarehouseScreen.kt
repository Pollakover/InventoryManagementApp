package com.example.inventorymanagementapp.screens.dialogs

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.*
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun NewWarehouseScreen(state: MutableState<Boolean>, viewModel: MainViewModel) {
    var warehouseName by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var postal_address by remember { mutableStateOf(TextFieldValue("")) }

    val user_login = "test1"

    Column(
        modifier = Modifier
            .padding(0.dp, 20.dp, 0.dp, 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Название склада",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = warehouseName,
                        onValueChange = { warehouseName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                        decorationBox = { innerTextField ->
                            if (warehouseName.text.isEmpty()) {
                                Text(
                                    text = "Введите название склада",
                                    color = MaterialTheme.colorScheme.onSecondary,
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
                        text = "Адрес",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = address,
                        onValueChange = { address = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                        decorationBox = { innerTextField ->
                            if (address.text.isEmpty()) {
                                Text(
                                    text = "Введиите адрес склада",
                                    color = MaterialTheme.colorScheme.onSecondary,
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
                        text = "Почтовый адрес",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = postal_address,
                        onValueChange = { postal_address = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                        decorationBox = { innerTextField ->
                            if (postal_address.text.isEmpty()) {
                                Text(
                                    text = "Введиите почтовый адрес",
                                    color = MaterialTheme.colorScheme.onSecondary,
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
                        onClick = { state.value = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Text("Отмена", style = CustomTextStyles.body2_medium)
                    }
                    val context = LocalContext.current
                    Button(onClick = {
                        if (checkWarehouseFields(context, warehouseName, address, postal_address)) {
                            viewModel.addWarehouse(
                                name = warehouseName.text,
                                address = address.text,
                                postal_address = postal_address.text,
                                user_login = user_login
                            )
                            viewModel.loadWarehouses(user_login)
                            state.value = false
                        }
                    },
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primary_500,
                            contentColor = white
                        ),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Text("Добавить склад", style = CustomTextStyles.body2_medium)
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewNewWarehouse() {
//    val clickOnAddButton = remember { mutableStateOf(false) }
//    NewWarehouseScreen(clickOnAddButton)
//}

fun checkWarehouseFields(
    context: Context,
    name: TextFieldValue,
    address: TextFieldValue,
    postal_address: TextFieldValue,

    ): Boolean {
    // Проверка на пустые поля
    if (name.text.isBlank() || address.text.isBlank()|| postal_address.text.isBlank()) {
        Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
        return false
    }
    else {
        Toast.makeText(context, "Склад добавлен", Toast.LENGTH_SHORT).show()
        return true
    }
}
