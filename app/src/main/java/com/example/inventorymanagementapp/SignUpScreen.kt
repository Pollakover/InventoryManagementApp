package com.example.inventorymanagementapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventorymanagementapp.ui.theme.*


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = white)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {

            //Логотип, верхний текст
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.icon),
                    modifier = Modifier.size(100.dp),
                    contentDescription = "Logo"
                )

                Text(
                    text = "Создайте аккаунт",
                    color = gray_900,
                    style = CustomTextStyles.heading1_semi_bold
                )
                Text(
                    text = "Заполните поля для регистрации.",
                    color = gray_500,
                    style = CustomTextStyles.body1_regular,
                )

            }

            //Поля ввода, кнопка, нижний текст
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Логин*",
                        color = gray_700,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = gray_100,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        decorationBox = {
                            Text(
                                text = "Введите логин",
                                color = gray_500,
                                style = CustomTextStyles.body1_regular
                            )
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Email*",
                        color = gray_700,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = gray_100,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        decorationBox = {
                            Text(
                                text = "Введите Email",
                                color = gray_500,
                                style = CustomTextStyles.body1_regular
                            )
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Пароль*",
                        color = gray_700,
                        style = CustomTextStyles.body2_medium
                    )
                    BasicTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = gray_100,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(14.dp, 10.dp, 14.dp, 10.dp),
                        decorationBox = {
                            Text(
                                text = "Придумайте пароль",
                                color = gray_500,
                                style = CustomTextStyles.body1_regular
                            )
                        }
                    )
                }

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primary_500,
                        contentColor = white
                    )
                ) {
                    Text(
                        "Зарегистрироваться",
                        color = white,
                        style = CustomTextStyles.body1_medium,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "У вас уже есть аккаунт?",
                        color = gray_500,
                        style = CustomTextStyles.body2_regular
                    )
                    Text(
                        text = "Войдите",
                        color = primary_600,
                        style = CustomTextStyles.body2_medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    SignUpScreen()
}