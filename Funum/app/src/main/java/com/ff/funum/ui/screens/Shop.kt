package com.ff.funum.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ff.funum.R
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.Green
import com.ff.funum.ui.theme.LightGreen
import com.ff.funum.ui.theme.White
import com.ff.funum.ui.theme.Chilanka
import com.ff.funum.ui.theme.GreenShop
import com.ff.funum.ui.theme.GreenTopics
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.ff.funum.model.Avatar

@Composable
fun Shop(navController: NavController, profileViewModel: ProfileViewModel = viewModel(), avatarViewModel: AvatarViewModel = viewModel()){

    val points by profileViewModel.points.collectAsState()
    val roles by profileViewModel.roles.collectAsState()
    val avatars by avatarViewModel.avatars.observeAsState(emptyList())
    var selectedAvatar by remember { mutableStateOf<Avatar?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        avatarViewModel.fetchAvatars()
    }

    LaunchedEffect(Unit) {
        profileViewModel.fetchUsername()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
    ){
        // Título
        Text(
            text = "Tienda de avatares",
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

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGreen)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Tus puntos:",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    color = White
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.picoins),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = points?.toString() ?: "Cargando...",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    color = White,
                    textAlign = TextAlign.Center
                ),
            )
        }

        // Mostrar botón "Agregar Avatar" si el usuario es admin
        if (roles.contains("admin")) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GreenTopics)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = { navController.navigate(Screens.AddAvatar.screen) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenTopics,
                        contentColor = White // Puedes ajustar este color según tus necesidades
                    )
                ) {
                    Text(
                        text = "Agregar Avatar",
                        style = TextStyle(
                            fontFamily = Chewy,
                            fontWeight = FontWeight.Normal,
                            fontSize = 25.sp,
                            color = White,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }
        }

        // Grid de items
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(GreenShop),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(avatars) { avatar ->
                AvatarItem(
                    avatar = avatar,
                    onAvatarClick = {
                        // Establecer el avatar seleccionado y mostrar el diálogo
                        selectedAvatar = avatar
                    },
                    onBuyClick = { imagen, costo ->
                        // Este onClick se llamará solo cuando se confirme la compra
                        avatarViewModel.buyAvatar(avatar.imagen, avatar.costo,
                            onSuccess = {
                                Toast.makeText(context, "Avatar comprado correctamente", Toast.LENGTH_SHORT).show()
                            },
                            onError = { error ->
                                Toast.makeText(context, "Error al comprar avatar: $error", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }

        }
    }
}

@Composable
fun AvatarItem (avatar: Avatar, onAvatarClick: () -> Unit, onBuyClick: (String, Int) -> Unit){
    var showDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .padding(8.dp)
            .background(DarkGreen)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del avatar
        Image(
            painter = rememberImagePainter(avatar.imagen),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .clickable {
                    // Mostrar el diálogo de confirmación al hacer clic en la imagen
                    showDialog = true
                }
        )

        // Texto del ítem
        Text(
            text = avatar.nombre,
            style = TextStyle(
                fontFamily = Chilanka,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = White
            ),
        )

        // Costo del avatar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.picoins),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${avatar.costo}",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    color = White,
                    textAlign = TextAlign.Center
                ),
            )
        }

        // Diálogo de confirmación
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Confirmación")
                },
                text = {
                    Column {
                        Image(
                            painter = rememberImagePainter(avatar.imagen),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "¿Deseas comprar este avatar por ${avatar.costo}?",
                            style = TextStyle(
                                fontFamily = Chewy,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = White,
                                textAlign = TextAlign.Center
                            ),
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            // Llamar a la función de clic del avatar
                            onBuyClick(avatar.imagen, avatar.costo)
                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            )
        }
    }
}





