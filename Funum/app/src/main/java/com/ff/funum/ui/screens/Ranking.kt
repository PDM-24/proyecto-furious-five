package com.ff.funum.ui.screens

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.ui.components.Podium
import com.ff.funum.ui.components.RankingCard
import com.ff.funum.model.UserDataModel
import com.ff.funum.model.UserList
import com.ff.funum.model.rankingData
import com.ff.funum.ui.components.LoadingProgressDialog
import com.ff.funum.ui.theme.FontChewy
import com.ff.funum.ui.theme.SecondaryDarkGreen

@Composable
fun Ranking(
    viewModel: LessonsViewModel,
    innerPadding: Dp = 0.dp
){
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getRanking()
    }

    when(uiState.value){
        is UiState.Error -> {
            val message = (uiState.value as UiState.Error).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            viewModel.setStateToReady()
        }
        UiState.Loading -> {
        }
        UiState.Ready -> {}
        is UiState.Success -> {}
    }

    if(viewModel.ranking.value.isEmpty()){
        LoadingProgressDialog()
    }
    else{
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
                viewModel.ranking.value[0],
                viewModel.ranking.value[1],
                viewModel.ranking.value[2]
            )

            Spacer(modifier = Modifier
                .height(22.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                itemsIndexed(viewModel.ranking.value){
                        index,  user->
                    RankingCard(user,(index+1))
                }

            }

        }
    }

}

@Composable
@Preview
fun RankingScreenPreview(){
    //Ranking()
}
