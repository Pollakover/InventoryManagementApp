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
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.inventorymanagementapp.ui.theme.*

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

    Column(modifier = Modifier.fillMaxSize(),Arrangement.spacedBy(20.dp)) {

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
                    .border(1.dp, color = gray_100, RoundedCornerShape(8.dp))
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
                        color = gray_600, // Цвет текста исправлен
                        fontSize = 14.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = transparent, // Фон при фокусе
                        unfocusedContainerColor = transparent,
                        disabledContainerColor = transparent,
                        focusedTextColor = gray_600, // Цвет текста исправлен
                        unfocusedTextColor = gray_600, // Цвет текста исправлен
                        cursorColor = gray_600, // Цвет курсора
                        focusedIndicatorColor = transparent, // Убираем черную линию
                        unfocusedIndicatorColor = transparent, // Убираем черную линию
                        disabledIndicatorColor = transparent // Убираем черную линию
                    ),
                    placeholder = {
                        Text(
                            "Поиск…",
                            style = CustomTextStyles.body2_regular,
                            color = gray_400
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
                                    color = gray_600, // Цвет текста
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

        val rainbowColors : List<Color> = listOf(Color(0xFF817AF3), Color(0xFF74B0FA), Color(0xFF79D0F1), Color(0xFF46A46C), Color(0xFF51CC5D), Color(0xFF57DA65))
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
            textStyle = TextStyle(brush = brush, fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Bold,),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = gray_50, // Фон при фокусе
                unfocusedContainerColor = white,
                disabledContainerColor = white,
                //focusedTextColor = gray_800, // Цвет текста
                //unfocusedTextColor = gray_800, // Цвет текста
                cursorColor = gray_800, // Цвет курсора
                focusedIndicatorColor = gray_500,
                unfocusedIndicatorColor = gray_500,
                disabledIndicatorColor = gray_500
            ),
            placeholder = {
                Text("Поиск…", style = CustomTextStyles.body2_regular, color = gray_400)
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNewProduct1() {
    Demo_ExposedDropdownMenuBox()
}