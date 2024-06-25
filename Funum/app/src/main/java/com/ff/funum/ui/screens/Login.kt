package com.ff.funum.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ff.funum.MainActivity
import com.ff.funum.R
import com.ff.funum.model.LoginData
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.GreenShop
import com.ff.funum.ui.theme.White
import com.ff.funum.utils.changeActivity

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {

    var loginData by remember { mutableStateOf(LoginData()) }

    val context = LocalContext.current

    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value is UiStateAuth.Error) {
        when ((uiState.value as UiStateAuth.Error).code) {
            400 -> {
                showMessage(context, "Error: usuario o contraseña incorrecta")
            }
        }
        viewModel.setStateToReady()
    }


    if (uiState.value is UiStateAuth.Success) {
        showMessage(context, "Logueo Exitoso")
        changeActivity(context, MainActivity::class.java)
        viewModel.setStateToReady()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val focusManager = LocalFocusManager.current

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

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    //modifier = Modifier.aspectRatio(4/3f),
                    painter = painterResource(id = R.drawable.white_logo),
                    contentDescription = "Logo"
                )
                Spacer(modifier = Modifier.size(50.dp))
                Text(
                    "Ingresa tus datos",
                    fontSize = 40.sp,
                    color = White,
                    fontFamily = Chewy
                )
            }
        }

        Column() {
            TextField(
                value = loginData.identifier,
                onValueChange = { data -> loginData = loginData.copy(identifier = data) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                label = { Text("Ingrese su usuario o su correo", color = Color.Gray) },
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.padding(20.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = loginData.pwd,
                    onValueChange = { data -> loginData = loginData.copy(pwd = data) },
                    trailingIcon = trailingIcon,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    ),
                    label = { Text("Ingrese su contraseña", color = Color.Gray) },
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(20.dp)
                )
            }
            LabeledCheckbox(
                onCheckChanged = {
                    loginData = loginData.copy(remember = !loginData.remember)
                },
                isChecked = loginData.remember
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = { viewModel.checkLogin(loginData) },
                colors = ButtonDefaults.buttonColors(containerColor = GreenShop)
            ) {
                Text(text = "INGRESAR")
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "¿No tienes una cuenta?", color = White)
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = GreenShop)
            ) {
                Text(text = "REGISTRATE AQUI")
            }
        }
    }
}

fun showMessage(
    context: Context,
    msg: String
) {
    Toast.makeText(
        context,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun LabeledCheckbox(
    onCheckChanged: () -> Unit,
    isChecked: Boolean
) {
    Row(
        Modifier
            .clickable(
                onClick = onCheckChanged
            )
            .padding(4.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(Modifier.size(6.dp))
        Text("Recuerdame")
    }
}