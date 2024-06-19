package com.ff.funum.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.ui.components.Podium
import com.ff.funum.ui.components.RankingCard
import com.ff.funum.model.UserDataModel
import com.ff.funum.model.UserList
import com.ff.funum.ui.theme.FontChewy
import com.ff.funum.ui.theme.SecondaryDarkGreen



@Composable
fun Ranking(){
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


        Spacer(modifier = Modifier
            .height(10.dp))
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

        Spacer(modifier = Modifier
            .height(22.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            itemsIndexed(UserList.subList(0,9)){
                index,  user->
                RankingCard(user,(index+1))
            }

        }

    }
}

@Composable
@Preview
fun RankingScreenPreview(){
    Ranking()
}
