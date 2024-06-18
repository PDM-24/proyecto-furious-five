package com.ff.funum.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun Shop(){
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
                text = "250",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    color = White,
                    textAlign = TextAlign.Center
                ),
            )
        }

        // Grid de items
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(GreenShop),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(11) { index ->
                AvatarItem(
                    imageResId = when (index) {
                        0 -> R.drawable.avatar1
                        1 -> R.drawable.avatar2
                        2 -> R.drawable.avatar3
                        3 -> R.drawable.avatar4
                        4 -> R.drawable.avatar5
                        5 -> R.drawable.avatar6
                        6 -> R.drawable.avatar7
                        7 -> R.drawable.avatar8
                        8 -> R.drawable.avatar9
                        9 -> R.drawable.avatar10
                        10 -> R.drawable.avatar11
                        else -> R.drawable.avatar1
                    },
                    cost = 50
                )
            }
        }


    }
}

@Composable
fun AvatarItem (imageResId : Int, cost: Int){
    Column (
        modifier = Modifier
            .padding(8.dp)
            .background(DarkGreen)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
        )
        Text(
            text = "Item",
            style = TextStyle(
                fontFamily = Chilanka,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = White
            ),
        )
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
                text = "$cost",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    color = White,
                    textAlign = TextAlign.Center
                ),
            )
        }

    }
}