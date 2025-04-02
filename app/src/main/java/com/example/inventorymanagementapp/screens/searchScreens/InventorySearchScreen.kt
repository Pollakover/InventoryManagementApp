package com.example.inventorymanagementapp.screens.searchScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagementapp.NoResults
import com.example.inventorymanagementapp.QueryError
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.data.models.Product
import com.example.inventorymanagementapp.screens.ProductRow
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles
import com.example.inventorymanagementapp.ui.theme.primary_500
import com.example.inventorymanagementapp.ui.theme.success_500
import com.example.inventorymanagementapp.viewModels.MainViewModel

@Composable
fun InventorySearchScreen(viewModel: MainViewModel) {
    val products by viewModel.products.collectAsState()
    var isFocused by remember { mutableStateOf(false) }
    val searchText by viewModel.searchText.collectAsState()
    val historyList = listOf(
        TextFieldValue("Истор1"),
        TextFieldValue("Истор2"),
        TextFieldValue("Истор3")
    ) // Заглушка истории

    Column(
        modifier = Modifier
            .padding(22.dp)
            .fillMaxSize(),
    ) {
        if(searchText.text.isBlank()){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "История поиска:",
                    style = CustomTextStyles.body2_regular,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {  },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clear_icon),
                        contentDescription = "Очистить историю",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {

                            }
                    )
                }
            }
            Column{
                historyList.forEach { item ->

                    TextButton(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                        onClick = {
                            viewModel.onSearchTextChange(item)
                            isFocused = false
                        }
                    ) {
                        Text(
                            text = item.text,
                            color = Red,
                            style = CustomTextStyles.body2_medium,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }

        } else {
            if(viewModel.error) {
                QueryError(viewModel)
            }

            else {
                if(products.isEmpty()) {
                    NoResults()
                }
                else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(products) { product ->
                            SearchResultRow(product)
                        }
                    }
                }
            }
        }

//        if(viewModel.error) {
//            QueryError(viewModel)
//        }
//
//        else {
//            if(products.isEmpty()) {
//                NoResults()
//            }
//            else {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    items(products) { product ->
//                        SearchResultRow(product)
//                    }
//                }
//            }
//        }

//        Column{
//            historyList.forEach { item ->
//
//                TextButton(
//                    shape = RoundedCornerShape(8.dp),
//                    modifier = Modifier.fillMaxWidth(),
//                    contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
//                    onClick = {
//                        viewModel.onSearchTextChange(item)
//                        isFocused = false
//                    }
//                ) {
//                    Text(
//                        text = item.text,
//                        color = MaterialTheme.colorScheme.onSurface,
//                        style = CustomTextStyles.body2_medium,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun SearchResultRow(product: Product) {
    TextButton(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
        onClick = {

        }
    ) {
        Text(
            text = product.name,
            color = MaterialTheme.colorScheme.onSurface,
            style = CustomTextStyles.body2_medium,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    val mainViewModel = viewModel<MainViewModel>()
    InventorySearchScreen(mainViewModel)
}