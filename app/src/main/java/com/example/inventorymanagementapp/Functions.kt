package com.example.inventorymanagementapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles
import com.example.inventorymanagementapp.ui.theme.gray_100
import com.example.inventorymanagementapp.ui.theme.gray_500
import com.example.inventorymanagementapp.ui.theme.primary_500
import com.example.inventorymanagementapp.ui.theme.white
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun NoResults(){
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = gray_100,
            modifier = Modifier.size(100.dp)
        )
        Text(
            "По вашему запросу ничего не найдено",
            color = gray_500,
            style = CustomTextStyles.body2_regular,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun QueryError(viewModel: MainViewModel){
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Произошла ошибка при выполнении запроса",
            color = gray_500,
            style = CustomTextStyles.body2_regular,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {viewModel.error = false},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primary_500,
                contentColor = white
            )
        ) {
            Text(
                "Обновить",
                color = white,
                style = CustomTextStyles.body1_medium,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}