package com.ff.funum.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ff.funum.R
import com.ff.funum.data.api.RegisterApi
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.GreenShop
import com.ff.funum.ui.theme.White

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    var registerData by remember { mutableStateOf(RegisterApi()) }
    fun clearFields() {
        registerData = RegisterApi()
    }
    val context = LocalContext.current
    val registerSuccess by viewModel.registerSuccess.observeAsState()

    LaunchedEffect(registerSuccess) {
        registerSuccess?.let {
            if (it) {
                Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()
                clearFields()
            } else {
                Toast.makeText(context, "Registro fallido, revise sus credenciales", Toast.LENGTH_LONG).show()
            }
        }
    }

    var isPasswordVisible by remember { mutableStateOf(false) }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                painter = if (isPasswordVisible)
                    painterResource(id = R.drawable.ic_visibility_off)
                else
                    painterResource(id = R.drawable.ic_visibility_on),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.white_logo),
                    contentDescription = "Logo"
                )
                Spacer(modifier = Modifier.size(50.dp))
                Text(
                    "¡Únete a nosotros!",
                    fontSize = 40.sp,
                    color = White,
                    fontFamily = Chewy
                )
            }
            Column(modifier = Modifier.padding(20.dp)) {
                TextField(
                    value = registerData.nombre,
                    onValueChange = { updatedString ->
                        registerData = registerData.copy(nombre = updatedString)
                    },
                    label = { Text("Ingrese su usuario") },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    value = registerData.correo,
                    onValueChange = { updatedString ->
                        registerData = registerData.copy(correo = updatedString)
                    },
                    label = { Text("Ingrese su correo") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = registerData.password,
                    onValueChange = { updatedString ->
                        registerData = registerData.copy(password = updatedString)
                    },
                    trailingIcon = trailingIcon,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = { Text("Ingrese su contraseña") },
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        viewModel.registerUser(registerData)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenShop)
                ) {
                    Text(text = "REGISTRARSE")
                }
                Spacer(modifier = Modifier.size(30.dp))
                Text(text = "¿Ya tienes una cuenta?", color = White)
                Spacer(modifier = Modifier.size(5.dp))
                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenShop)
                ) {
                    Text(text = "INICIA SESION AQUI")
                }
            }
        }
    }
}