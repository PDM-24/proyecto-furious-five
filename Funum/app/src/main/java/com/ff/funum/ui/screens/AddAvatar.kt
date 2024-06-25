package com.ff.funum.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ff.funum.data.repository.Repository
import com.ff.funum.model.Avatar
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.Green
import com.ff.funum.ui.theme.GreenTopics
import com.ff.funum.ui.theme.LightGray
import com.ff.funum.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAvatar(navController: NavController, avatarViewModel: AvatarViewModel = viewModel()) {
    var avatarName by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA8DADC))
    ) {
        Text(
            text = "Agregar Avatar",
            style = TextStyle(
                fontFamily = Chewy,
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                color = White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Green)
                .padding(18.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFA8DADC))
                .padding(16.dp)
        ){
            Text(
                text = "Nombre",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = Green,
                    textAlign = TextAlign.Center
                ),
            )
            TextField(
                value = avatarName,
                onValueChange = { avatarName = it },
                placeholder = {
                    Text(
                        text = "Escribe el nombre del avatar",
                        style = TextStyle(
                            fontFamily = Chewy,
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            color = LightGray,
                            textAlign = TextAlign.Center
                        ),
                    )
                },
                textStyle = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Imagen",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = Green,
                    textAlign = TextAlign.Center
                )
            )
            TextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                placeholder = {
                    Text(
                        text = "Escribe el URL de la imagen",
                        style = TextStyle(
                            fontFamily = Chewy,
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            color = LightGray,
                            textAlign = TextAlign.Center
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Precio",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = Green,
                    textAlign = TextAlign.Center
                )
            )
            TextField(
                value = price,
                onValueChange = { price = it },
                placeholder = {
                    Text(
                        text = "Escribe el precio del avatar",
                        style = TextStyle(
                            fontFamily = Chewy,
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            color = LightGray,
                            textAlign = TextAlign.Center
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = LightGray
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "CANCELAR",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = Chewy,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = LightGray,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Button(
                    onClick = {
                        val newAvatar = Avatar(
                            nombre = avatarName,
                            imagen = imageUrl,
                            costo = price.toIntOrNull() ?: 0
                        )
                        avatarViewModel.addAvatar(newAvatar,
                            onSuccess = {
                                navController.popBackStack()
                            },
                            onError = { errorMessage ->
                                // Aquí puedes manejar el error, como mostrar un Snackbar o Toast
                                Log.e("AddAvatar", "Error al agregar avatar: $errorMessage")
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "CONFIRMAR",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = Chewy,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = LightGray,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

