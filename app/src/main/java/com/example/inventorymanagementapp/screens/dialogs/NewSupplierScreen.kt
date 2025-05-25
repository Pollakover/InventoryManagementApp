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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun NewSupplierScreen(state: MutableState<Boolean>, viewModel : MainViewModel) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var phone_number by remember { mutableStateOf(TextFieldValue("")) }
    var selectedTypeIndex by remember { mutableIntStateOf(0) }

    val userLogin = "test1"

    val type = when (selectedTypeIndex) {
        0 -> "Принимает возврат"
        1 -> "Не принимает возврат"
        else -> "Принимает возврат"
    }

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
                        text = "Имя поставщика",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = name,
                        onValueChange = { newText ->
                            if (newText.text.length <= 25) {
                                name = newText
                            }
                        },
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
                            if (name.text.isEmpty()) {
                                Text(
                                    text = "Введите имя поставщика",
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
                        text = "Номер телефона",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = phone_number,
                        onValueChange = { newText ->
                            if (newText.text.length <= 50) {
                                phone_number = newText
                            }
                        },
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
                            if (phone_number.text.isEmpty()) {
                                Text(
                                    text = "Введиите номер телефона",
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
                        text = "Выберите тип",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )
                    SingleChoiceSegmentedButton(
                        selectedIndex = selectedTypeIndex,
                        onSelectionChanged = { index -> selectedTypeIndex = index }
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
                    Button(
                        onClick = {
                            if(checkFields(context, name, phone_number)){
                                viewModel.addSupplier(
                                    name = name.text,
                                    phone_number = phone_number.text,
                                    type = type,
                                    userLogin = userLogin
                                )
                                viewModel.loadSuppliers(userLogin)
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
                        Text("Добавить поставщика", style = CustomTextStyles.body2_medium)
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewNewSupplier() {
//    val clickOnAddButton = remember { mutableStateOf(false) }
//    NewSupplierScreen(clickOnAddButton)
//}

@Composable
fun SingleChoiceSegmentedButton(
    selectedIndex: Int,
    onSelectionChanged: (Int) -> Unit
) {
    val options = listOf("С возвратом", "Без возврата")

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size,
                    baseShape = RoundedCornerShape(8.dp)
                ),
                onClick = { onSelectionChanged(index) },
                selected = index == selectedIndex,
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = success_500,
                    activeContentColor = white,
                    activeBorderColor = transparent,
                    inactiveContainerColor = MaterialTheme.colorScheme.surface,
                    inactiveContentColor = MaterialTheme.colorScheme.onSecondary,
                    inactiveBorderColor = transparent
                ),
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        }
    }
}
fun checkFields(
    context: Context,
    name: TextFieldValue,
    phone_number: TextFieldValue,

): Boolean {
    // Проверка на пустые поля
    if (name.text.isBlank() || phone_number.text.isBlank()) {
        Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
        return false
    }
    else {
        Toast.makeText(context, "Поставщик добавлен", Toast.LENGTH_SHORT).show()
        return true
    }
}