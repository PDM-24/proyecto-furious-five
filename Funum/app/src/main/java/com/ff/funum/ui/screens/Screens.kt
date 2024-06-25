package com.ff.funum.ui.screens

sealed class Screens (val screen: String){
    data object Home: Screens("home")
    data object Profile: Screens("profile")
    data object Ranking: Screens("ranking")
    data object Shop: Screens("shop")
    data object Config: Screens("config")
    data object Quiz: Screens("quiz")
    data object Topic: Screens("topic")
    data object UpdateLesson: Screens("updatelesson")
    data object UpdateTopic: Screens("updatetopic")
}
