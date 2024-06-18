package com.ff.funum.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.component.ranking.Podium
import com.ff.funum.component.ranking.RankingCard
import com.ff.funum.model.UserDataModel
import com.ff.funum.model.UserList
import com.ff.funum.ui.theme.FontChewy
import com.ff.funum.ui.theme.PurpleGrey40
import com.ff.funum.ui.theme.SecondaryDarkGreen



@Composable
fun RankingScreen(){
    Column(modifier = Modifier

        .fillMaxSize()
        .background(SecondaryDarkGreen),
        horizontalAlignment = Alignment.CenterHorizontally


        ) {

        Text(modifier = Modifier
            .padding(24.dp),
            text = "Clasificación semanal",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            style = FontChewy,
            color = Color.White

        )

        Podium(
            UserDataModel(
            1,
            "Melissa Avelar",
            "natalia@gmail.com",
            R.drawable.puppies,
            2000
        ), UserDataModel(
                1,
                "Melissa Avelar",
                "natalia@gmail.com",
                R.drawable.puppies,
                2000
            ), UserDataModel(
                1,
                "Melissa Avelar",
                "natalia@gmail.com",
                R.drawable.puppies,
                2000
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            itemsIndexed(UserList.subList(0,11)){
                index,  user->
                RankingCard(user,(index+1))
            }

        }

    }
}

@Composable
@Preview
fun RankingScreenPreview(){
    RankingScreen()
}
