package com.ff.funum.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.model.Pregunta_opcion_multiple
import com.ff.funum.model.Respuesta_opcion_multiple

@Composable
fun MultipleChoiceQuestion(
    currentQuestionState: Pregunta_opcion_multiple,
    selectedAnswer: String?,
    resolved: Boolean,
    correctAnswer: String,
    onOptionSelected: (String) -> Unit,
    onResolve: () -> Unit,
    onNext: () -> Unit
) {
    Text(
        text = currentQuestionState.enunciado,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White,
        textAlign = TextAlign.Center
    )
    OptionList(
        options = currentQuestionState.respuestas.toList(),
        onOptionSelected = onOptionSelected,
        selectedAnswer = selectedAnswer,
        resolved = resolved,
        correctAnswer = correctAnswer
    )
    CheckButton(
        resolved = resolved,
        onResolve = onResolve,
        onNext = onNext,
        selectedAnswer = selectedAnswer
    )
}

@Composable
fun OptionList(options: List<Respuesta_opcion_multiple>, onOptionSelected: (String) -> Unit, selectedAnswer: String?, resolved: Boolean, correctAnswer: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        options.forEach { option ->
            OptionItem(
                option = option.descripcion,
                onOptionSelected = onOptionSelected,
                selectedAnswer = selectedAnswer,
                resolved = resolved,
                correctAnswer = correctAnswer
            )
        }
    }
}

@Composable
fun OptionItem(option: String, onOptionSelected: (String) -> Unit, selectedAnswer: String?, resolved: Boolean, correctAnswer: String) {
    //colors = ButtonDefaults.buttonColors(Color.Blue) ,
    val itemColor =
        if (selectedAnswer == option && !resolved){
            Color(0xFF4FC3F7)
        }
        else if((selectedAnswer == option && selectedAnswer == correctAnswer) || (option == correctAnswer && resolved)){
            Color(0xFF4CAF50)
        }
        else if(selectedAnswer == option){
            Color(0xFFF44336)
        }
        else{
            colorResource(id = R.color.secondaryThird)
        }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onOptionSelected(option) },
        enabled = !resolved,
        colors = ButtonDefaults.buttonColors(
            containerColor = itemColor,
            disabledContainerColor = itemColor
        )
    ) {
        Text(
            text = option,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Composable
fun CheckButton(resolved: Boolean, onResolve: () -> Unit, onNext: () -> Unit, selectedAnswer: String?) {
    Button(
        modifier = Modifier
            .width(175.dp),
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.secondaryFirst)
        ),
        onClick = {
            if(resolved)
                onNext()
            else
                onResolve()
        },
        enabled = (selectedAnswer != null)
    ) {
        Text(
            color = Color.White,
            text =
            if(!resolved){
                "Comprobar"
            }else{
                "Siguiente"
            },
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}