package com.ff.funum.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ff.funum.R
import com.ff.funum.data.api.Lessons
import com.ff.funum.data.listLessons
import com.ff.funum.ui.components.LessonCard
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.FunumTheme
import com.ff.funum.ui.theme.Green2
import com.ff.funum.ui.theme.GreenShop
import com.ff.funum.ui.theme.White

@Composable
fun Home(
    viewModel: LessonsViewModel,
    profileViewModel: ProfileViewModel = viewModel(),
    onClick: () -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getAllLessons()
        if (viewModel.rol){
            viewModel.rol()
        }
    }
    val points by profileViewModel.points.collectAsState()
    val completedLessons by profileViewModel.completedLessons.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.fetchUsername()
    }

    FunumTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GreenShop)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .padding(10.dp)
                    .size(40.dp), contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.white_logo),
                        contentDescription = "Logo"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.book),
                        contentDescription = "Lecciones",
                        Modifier.size(30.dp)
                    )
                    Text(text = completedLessons?.toString() ?: "Cargando...", Modifier.padding(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.picoin),
                        contentDescription = "PIcoins",
                        Modifier.size(30.dp)
                    )
                    Text(text = points?.toString() ?: "Cargando...", Modifier.padding(10.dp))
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Green2)
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = "Lecciones", fontFamily = Chewy, fontSize = 40.sp)

            }
            if(viewModel.admin) {
                Row (Modifier.fillMaxWidth(), Arrangement.Center){

                    Button(colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),onClick = {
                        viewModel.updatedLesson =
                            Lessons(id = "", visibility = false); viewModel.lesson =
                        Lessons(id = "", visibility = false); navController.navigate(route = "${Screens.UpdateLesson.screen}/Agregar")
                    }) {
                        Text(text = "Agregar leccion", fontFamily = Chewy, color = White)
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "edit lesson",
                            tint = White
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listLessons) { lesson ->
                    LessonCard(lessons = lesson, viewModel, onClick, navController)
                }
            }
        }
    }
}