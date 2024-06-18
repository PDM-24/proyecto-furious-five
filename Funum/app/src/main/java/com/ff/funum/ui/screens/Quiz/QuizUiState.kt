package com.ff.funum.ui.screens.Quiz

data class QuizUiState(
    var currentQuestionIndex: Int = 0,
    var selectedAnswer: String? = null,
    var resolved: Boolean = false,
    var points: Int = 0
)