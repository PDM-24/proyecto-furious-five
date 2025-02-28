package com.ff.funum.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.Chilanka
import com.ff.funum.ui.theme.PrincipalLightGreen
import com.ff.funum.ui.theme.SecondaryDarkGreen
import com.ff.funum.ui.theme.SecondaryMediumGreen
import com.ff.funum.ui.theme.White

@Composable
fun TopicsScreen(
    viewModel: LessonsViewModel,
    navController: NavController
) {
    val topic = viewModel.selectedTopic.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SecondaryMediumGreen),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(PrincipalLightGreen)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                topic.value.nombre,
                fontFamily = Chewy,
                color = White,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(10.dp, 10.dp, 10.dp, 150.dp)
                .fillMaxHeight()
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier
                .padding(8.dp)
                .verticalScroll(
                    rememberScrollState()
                )
                .weight(3f)) {
                Text(text = "Resumen", fontFamily = Chilanka, color = White, fontSize = 30.sp)
                Text(
                    text = topic.value.contenido,
                    fontFamily = Chilanka,
                    color = White,
                    fontSize = 20.sp
                )
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)){
                    AsyncImage(model = topic.value.imagen.first(), contentDescription = "Imagen Tema", modifier = Modifier.size(200.dp), contentScale = ContentScale.Crop)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp)
                    .weight(1f),
                    horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    navController.navigate(Screens.Home.screen)
                    viewModel.endTopic(topic.value.id)
                }, colors = ButtonDefaults.buttonColors(containerColor = SecondaryDarkGreen)) {
                    Text(text = "FINALIZAR TEMA", color = White, fontSize = 15.sp)
                }
            }
        }
    }
}