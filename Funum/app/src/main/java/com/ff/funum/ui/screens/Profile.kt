package com.ff.funum.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.ff.funum.ui.theme.LightGray
import com.ff.funum.ui.theme.ProgressBar
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.ff.funum.model.User



@Composable
fun Profile(navController: NavController, profileViewModel: ProfileViewModel = viewModel()){

    val username by profileViewModel.username.collectAsState()
    val completedLessons by profileViewModel.completedLessons.collectAsState()
    val totalLessons by profileViewModel.totalLessons.collectAsState()
    val availableAvatars by profileViewModel.availableAvatars.collectAsState()
    val currentAvatar by profileViewModel.currentAvatar.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.fetchUsername()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(GreenTopics)
                .padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter =  rememberImagePainter(currentAvatar.ifEmpty { R.drawable.avatar3 }),
                    contentDescription = null,
                    modifier = Modifier
                        .size(220.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
                IconButton(
                    onClick = {
                        navController.navigate(Screens.Config.screen)
                    },
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.config),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }

        StatisticsSection(username)
        UnlockedAvatarsSection(availableAvatars)
    }
}



@Composable
fun StatisticsSection(username: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Green)
            .padding(16.dp)
    ) {
        Text(
            text = username ?: "Cargando...",
            style = TextStyle(
                fontFamily = Chilanka,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                color = White
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Estadísticas",
            style = TextStyle(
                fontFamily = Chewy,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                color = White
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = 0.3f,
            color = ProgressBar,
            trackColor = White,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Lecciones completadas",
            style = TextStyle(
                fontFamily = Chilanka,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = White
            )
        )
    }
}

@Composable
fun UnlockedAvatarsSection(availableAvatars: Array<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green)
            .padding(16.dp)
    ) {
        Text(
            text = "Avatares desbloqueados",
            style = TextStyle(
                fontFamily = Chewy,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                color = White
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            availableAvatars.forEach { avatar ->
                Image(
                    painter = rememberImagePainter(avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}