package com.example.inventorymanagementapp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles
import java.util.Locale.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val categories = arrayOf("Food", "Drinks", "Help", "Settings", "About")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(categories[0]) }

    val openDialog = remember { mutableStateOf(true) }
    //val dialogWidth = 200.dp
    //val dialogHeight = 50.dp

    Dialog(onDismissRequest = { openDialog.value = false }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Box(Modifier.padding(0.dp, 20.dp, 0.dp, 20.dp).clip(RoundedCornerShape(8.dp))){
            NewOrderScreen()
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent) // Убираем фон у самой коробки
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(R.color.warning_400)) // Цвет фона TextField
                    .padding(8.dp),
                textStyle = TextStyle(
                    color = Color.White, // Цвет текста
                    fontSize = 16.sp // Размер шрифта
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.warning_400), // Фон при фокусе
                    unfocusedContainerColor = colorResource(R.color.warning_400),
                    disabledContainerColor = colorResource(R.color.warning_400),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(colorResource(R.color.warning_500)) // Фон выпадающего списка
                    .clip(RoundedCornerShape(8.dp))
            ) {
                categories.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = Color.White, // Цвет текста
                                fontSize = 14.sp,
                                modifier = Modifier.padding(0.dp) // Внутренние отступы
                            )
                        },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(R.color.warning_500)) // Цвет фона каждого элемента
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNewProduct1() {
    Demo_ExposedDropdownMenuBox()
}