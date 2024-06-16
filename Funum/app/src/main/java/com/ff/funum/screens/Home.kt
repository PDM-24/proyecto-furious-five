package com.ff.funum.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.data.listLessons
import com.ff.funum.ui.components.LessonCard
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.FunumTheme
import com.ff.funum.ui.theme.Green2
import com.ff.funum.ui.theme.GreenShop

@Composable
fun Home(
    viewModel: LessonsViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.getAllLessons()
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
                    Text(text = "0", Modifier.padding(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.picoin),
                        contentDescription = "PIcoins",
                        Modifier.size(30.dp)
                    )
                    Text(text = "0", Modifier.padding(10.dp))
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listLessons) { lesson ->
                    LessonCard(lessons = lesson)
                }
            }
        }
    }
}