package com.example.inventorymanagementapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanagementapp.ui.theme.CustomTextStyles
import com.example.inventorymanagementapp.ui.theme.InventoryManagementAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            InventoryManagementAppTheme {
                AppScaffold()
            }
        }
    }
}

@Composable
fun AppScaffold() {
    ModalNavigationDrawer(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = colorResource(R.color.white))) {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Column(modifier = Modifier.background(color = colorResource(R.color.gray_50)).padding(24.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Image(
                                painter = painterResource(R.drawable.profile_placeholder),
                                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(50)).border(1.dp, color = colorResource(R.color.gray_100), RoundedCornerShape(50)),
                                contentDescription = "Logo"
                            )

                            //Имя и почта
                            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
                            {
                                Text("Алексей Поляков", color = colorResource(R.color.gray_600), style = CustomTextStyles.body1_bold)
                                Text("revokalloppollakover@gmail.com", color = colorResource(R.color.gray_400), fontSize = 12.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.5.sp)
                            }
                        }
                        Column(modifier = Modifier.padding(24.dp, 0.dp, 24.dp, 0.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {

                            Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                                Icon(
                                    painter = painterResource(id = R.drawable.home_icon),
                                    contentDescription = "",
                                    tint = colorResource(R.color.primary_500),
                                )

                                Text(text="Приборная панель", style = CustomTextStyles.body1_medium, color = colorResource(R.color.primary_500))
                            }

                            Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                                Icon(
                                    painter = painterResource(id = R.drawable.inventory_icon),
                                    contentDescription = "",
                                    tint = colorResource(R.color.gray_600),
                                )

                                Text(text="Инвентарь", style = CustomTextStyles.body1_medium, color = colorResource(R.color.gray_600))
                            }

                            Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                                Icon(
                                    painter = painterResource(id = R.drawable.suppliers_icon),
                                    contentDescription = "",
                                    tint = colorResource(R.color.gray_600),
                                )

                                Text(text="Поставщики", style = CustomTextStyles.body1_medium, color = colorResource(R.color.gray_600))
                            }

                            Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                                Icon(
                                    painter = painterResource(id = R.drawable.orders_icon),
                                    contentDescription = "",
                                    tint = colorResource(R.color.gray_600),
                                )

                                Text(text="Заказы", style = CustomTextStyles.body1_medium, color = colorResource(R.color.gray_600))
                            }

                            Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                                Icon(
                                    painter = painterResource(id = R.drawable.warehouses_icon),
                                    contentDescription = "",
                                    tint = colorResource(R.color.gray_600),
                                )

                                Text(text="Склады", style = CustomTextStyles.body1_medium, color = colorResource(R.color.gray_600))
                            }

                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(modifier = Modifier.padding(24.dp, 0.dp, 48.dp, 0.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {

                        Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 48.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                            Icon(
                                painter = painterResource(id = R.drawable.logout_icon),
                                contentDescription = "",
                                tint = colorResource(R.color.gray_600),
                            )

                            Text(text="Выйти", style = CustomTextStyles.body1_medium, color = colorResource(R.color.gray_600))
                        }
                    }
                }

            }
        },
        //modifier = TODO(),
        //gesturesEnabled = TODO(),
        //scrimColor = TODO()
    ) { }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    AppScaffold()
}