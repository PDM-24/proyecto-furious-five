package com.ff.funum.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.model.UserDataModel
import com.ff.funum.ui.theme.FontChilanka
import com.ff.funum.ui.theme.PrincipalLightGreen

@Composable
fun RankingCard(user: UserDataModel, index: Int){
    Card(modifier = Modifier
        .fillMaxWidth()
        .background(PrincipalLightGreen)) {
        Row(modifier = Modifier
                .background(PrincipalLightGreen)
            .height(38.dp),
            verticalAlignment =Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp,0.dp,6.dp,0.dp),
                text = index.toString(),
                color = Color.White,
                style = FontChilanka
            )
            val borderWidth = 0.05.dp
            Image(
                painter = painterResource(id = user.imagen),
                contentDescription = "Foto de perfil del jugador",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .padding(borderWidth, 0.dp)
                    .border(
                        BorderStroke(borderWidth, Color.White),
                        CircleShape
                    )



            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = user.nombre,
                color = Color.White,
                style = FontChilanka, fontSize = 18.sp
            )
            Spacer(
                modifier = Modifier
                .weight(1f))
            Text(
                modifier = Modifier
                    .padding(10.dp,0.dp),
                text = user.puntos.toString(),
                color = Color.White,
                style = FontChilanka,)
        }
    }
}

@Composable
@Preview
fun PreviewRankingCard(){
    RankingCard(UserDataModel(
        1,
        "Melissa Avelar",
        "natalia@gmail.com",
        R.drawable.puppies,
        2000
    ), 1)
}