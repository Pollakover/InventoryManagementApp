package com.example.inventorymanagementapp.database.login

//import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanagementapp.R
import com.example.inventorymanagementapp.database.ApiClient
import com.example.inventorymanagementapp.ui.theme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignUpScreen(navController: NavController) {

    var login by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.surface)
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
                    color = MaterialTheme.colorScheme.onSurface,
                    style = CustomTextStyles.heading1_semi_bold
                )
                Text(
                    text = "Заполните поля для регистрации.",
                    color = MaterialTheme.colorScheme.onSecondary,
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
                        text = "Логин",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )

                    BasicTextField(
                        value = login,
                        onValueChange = { login = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp)
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
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (login.isEmpty()) {
                                    Text(
                                        text = "Введите логин",
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        style = CustomTextStyles.body1_regular
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "E-mail",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )

                    BasicTextField(
                        value = email,
                        onValueChange = {email = it},
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
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (email.isEmpty()) {
                                    Text(
                                        text = "Введите e-mail",
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        style = CustomTextStyles.body1_regular
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Пароль",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = CustomTextStyles.body2_medium
                    )

                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
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
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (password.isEmpty()) {
                                    Text(
                                        text = "Придумайте пароль",
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        style = CustomTextStyles.body1_regular
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                val context = LocalContext.current
                Button(
                    onClick = {
                        if(login.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                            registerUser(login, password, email, context)
                        } else {
                            Toast.makeText(context, "Заполните все поля", Toast.LENGTH_LONG).show()
                        }
                    },
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
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = CustomTextStyles.body2_regular
                    )
                    Text(
                        text = "Войдите",
                        color = primary_500,
                        style = CustomTextStyles.body2_medium,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}
const val TAG = "RegisterUser"
private fun registerUser(login: String, password: String, email: String, context: Context) {
    val call = ApiClient.authApi.register(RegisterRequest(login, password, email))
    call.enqueue(object : Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            if (response.isSuccessful) {
                Toast.makeText(context, "Успешная регистрация", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Toast.makeText(context, "Ошибка uj: ${t.message}", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "ERROR: ${t.message.toString()}")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}