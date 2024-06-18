package com.ff.funum

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ff.funum.ui.screens.Home
import com.ff.funum.ui.screens.LessonsViewModel
import com.ff.funum.ui.screens.Profile
import com.ff.funum.ui.screens.Quiz.QuizScreen
import com.ff.funum.ui.screens.Ranking
import com.ff.funum.ui.screens.Screens
import com.ff.funum.ui.screens.Shop
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.FunumTheme
import com.ff.funum.ui.theme.White

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : LessonsViewModel by viewModels()
        setContent {
            FunumTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyBottomAppBar(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MyBottomAppBar(viewModel: LessonsViewModel){
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(R.drawable.menu)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar (
                containerColor = DarkGreen
            ) {
                IconButton(onClick = {
                        selected.value = R.drawable.menu
                    navigationController.navigate(Screens.Home.screen){
                        popUpTo(0)
                    }
                },
                modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(id = R.drawable.menu),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .border(
                                width = 2.dp,
                                color = if (selected.value == R.drawable.menu) DarkGreen else Color.Transparent
                            ),
                            tint = Color.Unspecified)
                }
                IconButton(onClick = {
                    selected.value = R.drawable.ranking
                    navigationController.navigate(Screens.Ranking.screen){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(id = R.drawable.ranking),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp),
                        tint = Color.Unspecified)
                }
                IconButton(onClick = {
                    selected.value = R.drawable.picoins
                    navigationController.navigate(Screens.Shop.screen){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(id = R.drawable.picoins),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = Color.Unspecified)
                }
                IconButton(onClick = {
                    selected.value = R.drawable.profile
                    navigationController.navigate(Screens.Profile.screen){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = Color.Unspecified)
                }

            }
        }
    ) {paddingValues ->
        NavHost(navController = navigationController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)){
            composable(Screens.Home.screen){ Home(viewModel) }
            composable(Screens.Ranking.screen){ Ranking() }
            composable(Screens.Shop.screen){ Shop() }
            composable(Screens.Profile.screen){ Profile() }
            composable(
                route = "${Screens.Quiz.screen}/{examId}",
                arguments = listOf(
                    navArgument("examId"){
                        type = NavType.StringType
                    }
                )
            ){ backStackEntry ->
                QuizScreen(navController = navigationController, examId = backStackEntry.arguments?.getString("examId"))
            }
        }

    }
}

/*@Preview
@Composable
fun MyBottomBarPreview(){
    BottomAppBar {
        MyBottomAppBar()
    }
}*/