package com.ff.funum.screens

sealed class Screens (val screen: String){
    data object Home: Screens("home")
    data object Profile: Screens("profile")
    data object Ranking: Screens("ranking")
    data object Shop: Screens("shop")
    data object Config: Screens("config")
}