package com.ff.funum.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ff.funum.R
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.Green
import com.ff.funum.ui.theme.LightGray
import com.ff.funum.ui.theme.LightGreen
import com.ff.funum.ui.theme.White


@Composable
fun Config(navController: NavController) {
    var selectedAvatar by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Avatares",
            style = TextStyle(
                fontFamily = Chewy,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                color = White
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        AvatarGrid(
            selectedAvatar = selectedAvatar,
            onAvatarSelected = { selectedAvatar = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Screens.Profile.screen)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(
                text = "GUARDAR ELECCIÓN",
                style = TextStyle(
                    fontFamily = Chewy,
                    fontWeight = FontWeight.Normal,
                    fontSize = 30.sp,
                    color = White
                )
            )
        }
    }
}

@Composable
fun AvatarGrid(selectedAvatar: Int?, onAvatarSelected: (Int) -> Unit) {
    val avatars = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7,
    )

    Column {
        for (row in avatars.chunked(4)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                for (avatar in row) {
                    AvatarItem(
                        avatarResId = avatar,
                        isSelected = avatar == selectedAvatar,
                        onClick = { onAvatarSelected(avatar) }
                    )
                }
            }
        }
    }
}

@Composable
fun AvatarItem(avatarResId: Int, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = avatarResId),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )
    }
}