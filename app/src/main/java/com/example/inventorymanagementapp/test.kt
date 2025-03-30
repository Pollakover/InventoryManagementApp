package com.example.inventorymanagementapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles
import com.example.inventorymanagementapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val categories = arrayOf("Food", "Drinks", "Help", "Settings", "About")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(categories[0]) }

    //val openDialog = remember { mutableStateOf(true) }
    //val dialogWidth = 200.dp
    //val dialogHeight = 50.dp

    Column(modifier = Modifier.fillMaxSize(), Arrangement.spacedBy(20.dp)) {

        var text1 by remember { mutableStateOf("") }
        // val maxChar = 5

        TextField(
            value = text1,
            onValueChange = {
                text1 = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(vertical = 4.dp)
                .border(1.dp, color = MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(Icons.Filled.Search, "Floating action button.", tint = MaterialTheme.colorScheme.onPrimary)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background, // Фон при фокусе
                unfocusedContainerColor = white,
                disabledContainerColor = white,
                focusedTextColor = MaterialTheme.colorScheme.onSecondary, // Цвет текста
                unfocusedTextColor = MaterialTheme.colorScheme.onSecondary, // Цвет текста
                cursorColor = MaterialTheme.colorScheme.onSecondary, // Цвет курсора
                focusedIndicatorColor = transparent,
                unfocusedIndicatorColor = transparent,
                disabledIndicatorColor = transparent
            ),
            textStyle = CustomTextStyles.body2_regular,
            placeholder = {
                Text(
                    "Поиск…",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.fillMaxSize()
                )
            },
        )


        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Убираем фон у самой коробки
                    .height(80.dp)
                    .border(1.dp, color = MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
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
                        .padding(8.dp),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onTertiary, // Цвет текста исправлен
                        fontSize = 14.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = transparent, // Фон при фокусе
                        unfocusedContainerColor = transparent,
                        disabledContainerColor = transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onTertiary, // Цвет текста исправлен
                        unfocusedTextColor = MaterialTheme.colorScheme.onTertiary, // Цвет текста исправлен
                        cursorColor = MaterialTheme.colorScheme.onTertiary, // Цвет курсора
                        focusedIndicatorColor = transparent, // Убираем черную линию
                        unfocusedIndicatorColor = transparent, // Убираем черную линию
                        disabledIndicatorColor = transparent // Убираем черную линию
                    ),
                    placeholder = {
                        Text(
                            "Поиск…",
                            style = CustomTextStyles.body2_regular,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(white) // Фон выпадающего списка
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                ) {
                    categories.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item,
                                    color = MaterialTheme.colorScheme.onTertiary, // Цвет текста
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            onClick = {
                                selectedText = item
                                expanded = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        val rainbowColors: List<Color> = listOf(
            Color(0xFF817AF3),
            Color(0xFF74B0FA),
            Color(0xFF79D0F1),
            Color(0xFF46A46C),
            Color(0xFF51CC5D),
            Color(0xFF57DA65)
        )
        var text by remember { mutableStateOf("") }
        val brush = remember {
            Brush.linearGradient(
                colors = rainbowColors
            )
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                brush = brush,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background, // Фон при фокусе
                unfocusedContainerColor = white,
                disabledContainerColor = white,
                //focusedTextColor = MaterialTheme.colorScheme.onSecondary, // Цвет текста
                //unfocusedTextColor = MaterialTheme.colorScheme.onSecondary, // Цвет текста
                cursorColor = MaterialTheme.colorScheme.onSecondary, // Цвет курсора
                focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                disabledIndicatorColor = MaterialTheme.colorScheme.onSecondary
            ),
            placeholder = {
                Text("Поиск…", style = CustomTextStyles.body2_regular, color = MaterialTheme.colorScheme.onPrimary)
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNewProduct1() {
    Demo_ExposedDropdownMenuBox()
}