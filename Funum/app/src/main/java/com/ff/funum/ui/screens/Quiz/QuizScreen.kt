package com.ff.funum.ui.screens.Quiz

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ff.funum.R
import com.ff.funum.model.ExamData
import com.ff.funum.model.Pregunta_opcion_multiple
import com.ff.funum.model.Respuesta_match
import com.ff.funum.ui.components.LoadingProgressDialog
import com.ff.funum.ui.components.MatchQuestion
import com.ff.funum.ui.components.MultipleChoiceQuestion

@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel = viewModel(),
    innerPadding: Dp = 0.dp,
    navController: NavController,
    token: String? = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZmODJkODYyNTA0MDI1YWE2N2IzNWIiLCJleHAiOjE3MTk2MDUzMjAsImlhdCI6MTcxODMwOTMyMH0.YAPbZlCrhggIdJUtih0o3MbJvHX-xOGYxipTKH1C_Es",
    examId: String? = null
) {
    val QuizScreenState = quizViewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        quizViewModel.resetQuiz()
        if(token != null && examId != null){
            quizViewModel.getExam(token, examId = examId)
            quizViewModel.startExam(token, examId = examId)
        }
    }

    when(QuizScreenState.value){
        is UiState.Error -> {
            val message = (QuizScreenState.value as UiState.Error).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            quizViewModel.setStateToReady()
        }
        UiState.Loading -> {
        }
        UiState.Ready -> {}
        is UiState.Success -> {
            val message = (QuizScreenState.value as UiState.Success).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            quizViewModel.setStateToReady()
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.secondarySecond)
            )
            .padding(top = innerPadding, bottom = innerPadding)
    ) {
        if(QuizScreenState.value == UiState.Loading || quizViewModel.examState.value.lastIndex <= 0){
            LoadingProgressDialog()
        }else{
            if(
                quizViewModel.auxUiState.currentQuestionIndex > quizViewModel.examState.value.lastIndex){

                val correctAnswers = quizViewModel.auxUiState.points
                QuizResults(
                    correctAnswers,
                    quizViewModel.examState.value.size,
                    onEndQuiz = {
                        if(token != null){
                            if (examId != null) {
                                quizViewModel.finishExam(
                                    token = token,
                                    examId = examId,
                                    calificacion = ((correctAnswers * 10) / quizViewModel.examState.value.size)
                                )
                            }
                        }
                    }
                )

            }else{
                QuizQuestions(
                    quizLength = quizViewModel.examState.value.size,
                    quizUiState = quizViewModel.auxUiState,
                    currentQuestion = quizViewModel.getCurrentQuestion().question,
                    onOptionSelected = { selectedOption:String ->
                        quizViewModel.updateOptionSelected(selectedOption)
                    },
                    onResolve = {
                        quizViewModel.resolveQuestion()
                    },
                    onNext = {
                        quizViewModel.onNext()
                    },
                    correctAnswer = {quizViewModel.getMultipleChoiceCorrectAnswer()},
                    type = quizViewModel.getCurrentQuestion().type,
                    getMatchColumns = {quizViewModel.getMatchColumns()},
                    onResolveMatch = {correctMatches:MutableList<Boolean> ->
                        quizViewModel.resolveQuestion(correctMatches)
                    }
                )
            }
        }

    }
}

@Composable
fun QuizQuestions(
    quizLength: Int,
    quizUiState: QuizUiState,
    currentQuestion: Any,
    onOptionSelected: (String) -> Unit,
    onResolveMatch: ( MutableList<Boolean> ) -> Unit,
    onResolve: () -> Unit,
    onNext: () -> Unit,
    correctAnswer: () -> String,
    type: String,
    getMatchColumns: () -> Pair<List<Respuesta_match>, List<Respuesta_match>>
) {
    val quizProgress: Float = ((quizUiState.currentQuestionIndex + 1).toFloat() / quizLength.toFloat())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White, textAlign = TextAlign.Center
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Progreso del quiz",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White, textAlign = TextAlign.Center
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .height(15.dp)
                    .clip(RoundedCornerShape(8.dp))
                ,
                progress = quizProgress,
                trackColor = Color.White,
                color = colorResource(id = R.color.primaryFirst)
            )
        }
        if(type == "match"){
            MatchQuestion(
                getMatchColumns = getMatchColumns,
                onResolve = onResolveMatch,
                onNext = onNext,
                resolved = quizUiState.resolved
            )
        }else if (type == "multiple"){
            MultipleChoiceQuestion(
                currentQuestionState = currentQuestion as Pregunta_opcion_multiple,
                onOptionSelected = onOptionSelected,
                correctAnswer = correctAnswer(),
                resolved = quizUiState.resolved,
                selectedAnswer = quizUiState.selectedAnswer,
                onResolve = onResolve,
                onNext = onNext
            )
        }

    }
}

@Composable
fun QuizResults(
    correctAnswers: Int,
    quizLength: Int,
    onEndQuiz: () -> Unit = {}
) {
    val progress = (correctAnswers.toFloat() / quizLength.toFloat())
    val grade = (correctAnswers * 10) / quizLength

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White, textAlign = TextAlign.Center
        )
        Column (
            modifier = Modifier.width(275.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Text(text = "Correctas / Incorrectas")
            LinearProgressIndicator(
                modifier = Modifier
                    .height(23.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                ,
                progress = progress,
                trackColor = colorResource(id = R.color.progressWrong),
                color = colorResource(id = R.color.progressRight)
            )
        }

        Text(
            text = "Respuestas correctas: $correctAnswers/$quizLength",
            color = Color.White,
            fontSize = 20.sp
        )
        Text(
            text = "Puntos ganados: ${ExamData.ponderacion}",
            color = Color.White,
            fontSize = 20.sp
        )
        Text(
            text = "Nota: $grade/10",
            color = Color.White,
            fontSize = 20.sp
        )

        Button(
            modifier = Modifier.width(175.dp),
            onClick = onEndQuiz,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.secondaryFirst)
            )) {
            Text(
                text = "Volver",
                color = Color.White,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }

}