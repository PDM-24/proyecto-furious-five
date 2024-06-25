package com.ff.funum.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.ff.funum.R
import com.ff.funum.data.api.User_ranking
import com.ff.funum.model.UserDataModel
import com.ff.funum.ui.theme.Aqua
import com.ff.funum.ui.theme.ChilankaFont
import com.ff.funum.ui.theme.OldYellow
import com.ff.funum.ui.theme.Pink

@Composable
fun FirstPlace(user : User_ranking){
    Column (

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    )
    {
        val borderWidth = 0.05.dp
        AsyncImage(
            model = user.avatar_actual,
            contentDescription = "Foto del jugador en tercer lugar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .padding(borderWidth, 0.dp)


        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier
            .height(180.dp)
            .width(100.dp)
            .background(OldYellow)


        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = user.nombre, style = TextStyle(fontSize = 12.sp), fontFamily = ChilankaFont,
                    modifier = Modifier.padding(0.dp,6.dp, 0.dp,0.dp))
                Image(painter = painterResource(id = R.drawable.first), contentDescription = "medalla de tercer lugar",
                    modifier = Modifier
                        .size(42.dp))
            }
        }
    }
}

@Composable
fun SecondPlace(user : User_ranking){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    )
    {
        val borderWidth = 0.05.dp
        AsyncImage(model = user.avatar_actual, contentDescription = "Foto del jugador en tercer lugar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .padding(borderWidth, 0.dp)


        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier
            .height(145.dp)
            .width(100.dp)
            .background(Pink)


        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = user.nombre, style = TextStyle(fontSize = 12.sp), fontFamily = ChilankaFont,
                    modifier = Modifier.padding(0.dp,6.dp, 0.dp,0.dp))
                Image(painter = painterResource(id = R.drawable.second), contentDescription = "medalla de tercer lugar",
                    modifier = Modifier
                        .size(42.dp))
            }
        }
    }
}

@Composable
fun ThirdPlace(user : User_ranking){
    Column (

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    )
    {
        val borderWidth = 0.05.dp
        AsyncImage(model = user.avatar_actual, contentDescription = "Foto del jugador en tercer lugar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .padding(borderWidth, 0.dp)


                )
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier
            .height(120.dp)
            .width(100.dp)
            .background(Aqua)


        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = user.nombre, style = TextStyle(fontSize = 12.sp), fontFamily = ChilankaFont,
                    modifier = Modifier.padding(0.dp,6.dp, 0.dp,0.dp))
                Image(painter = painterResource(id = R.drawable.third), contentDescription = "medalla de tercer lugar",
                    modifier = Modifier
                        .size(42.dp))
            }
        }
    }
}

@Composable
fun Podium(first : User_ranking, second: User_ranking, third: User_ranking){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ThirdPlace(third)

        FirstPlace(first)
        SecondPlace(second)

    }
}
