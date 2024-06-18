package com.ff.funum.component.ranking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.model.UserDataModel
import com.ff.funum.ui.theme.Aqua
import com.ff.funum.ui.theme.ChilankaFont
import com.ff.funum.ui.theme.OldYellow
import com.ff.funum.ui.theme.Pink

@Composable
fun FirstPlace(user : UserDataModel){
    Column (
        modifier = Modifier
            .height(160.dp)
            .width(80.dp)
            .background(OldYellow),
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {
        Text(text = user.nombre, style = TextStyle(fontSize = 10.sp), fontFamily = ChilankaFont,
            modifier = Modifier.padding(0.dp,6.dp, 0.dp,0.dp))
        Image(painter = painterResource(id = R.drawable.first), contentDescription = "medalla de primer lugar",
            modifier = Modifier
                .size(32.dp))

    }
}

@Composable
fun SecondPlace(user : UserDataModel){
    Column (
        modifier = Modifier
            .height(120.dp)
            .width(80.dp)
            .background(Pink),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = user.nombre, style = TextStyle(fontSize = 10.sp), fontFamily = ChilankaFont,
            modifier = Modifier.padding(0.dp,6.dp, 0.dp,0.dp))
        Image(painter = painterResource(id = R.drawable.second), contentDescription = "medalla de segundo lugar",
            modifier = Modifier
                .size(32.dp))

    }
}

@Composable
fun ThirdPlace(user : UserDataModel){
    Column (
        modifier = Modifier
            .height(100.dp)
            .width(80.dp)
            .background(Aqua),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = user.nombre, style = TextStyle(fontSize = 10.sp), fontFamily = ChilankaFont,
            modifier = Modifier.padding(0.dp,6.dp, 0.dp,0.dp))
        Image(painter = painterResource(id = R.drawable.third), contentDescription = "medalla de tercer lugar",
            modifier = Modifier
                .size(32.dp))

    }
}

@Composable
fun Podium(first : UserDataModel, second: UserDataModel, third: UserDataModel){
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        ThirdPlace(third)

        FirstPlace(first)
        SecondPlace(second)

    }
}

@Composable
@Preview
fun podiumPreview(){
    Podium(UserDataModel(
        1,
        "Melissa Avelar",
        "natalia@gmail.com",
        R.drawable.puppies,
        2000
    ),
        UserDataModel(
            1,
            "Melissa Avelar",
            "natalia@gmail.com",
            R.drawable.puppies,
            2000
        ),
        UserDataModel(
            1,
            "Melissa Avelar",
            "natalia@gmail.com",
            R.drawable.puppies,
            2000
        ))
}