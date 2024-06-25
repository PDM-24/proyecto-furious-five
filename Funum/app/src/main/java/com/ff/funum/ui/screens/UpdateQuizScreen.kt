
package com.ff.funum.ui.screens

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ff.funum.data.api.Lessons
import com.ff.funum.data.api.Pregunta_opcion_multiple_Api
import com.ff.funum.data.api.Respuesta_opcion_multiple_api
import com.ff.funum.data.api.TemaApi
import com.ff.funum.model.Pregunta_opcion_multiple
import com.ff.funum.ui.theme.Black
import com.ff.funum.ui.theme.ChewyFont
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.FontChewy
import com.ff.funum.ui.theme.FontChilanka
import com.ff.funum.ui.theme.LightGreen
import com.ff.funum.ui.theme.White
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun UpdateMCQuestionComponent(accion: String,viewModel: LessonsViewModel, navController: NavController) {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(LightGreen),
        verticalArrangement = Arrangement.SpaceBetween,
       // horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(accion==="Agregar"){
            TitleQuiz(action = "Agregar Quiz",
                Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .height(65.dp)
                    //.weight(1f)
            )
        }else if(accion==="Actualizar"){
            TitleQuiz(action = "Actualizar quiz",
                Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    //.weight(1f)
            )



        }

        MCQuestionArea(modifier =
        Modifier
            .fillMaxWidth(),
            viewModel)
        Text(text = "Opciones", color = DarkGreen, textAlign = TextAlign.Left, fontSize = 35.sp, style = FontChewy, modifier = Modifier
            .padding(36.dp,0.dp,0.dp, 0.dp))
        MCCorrectAnswers(modifier = Modifier
            .padding(24.dp), viewModel)
        MCIncorrectAnswer1(modifier =Modifier , viewModel )
        MCIncorrectAnswer2(modifier = Modifier, viewModel )
        MCIncorrectAnswer3(modifier = Modifier, viewModel)

        Row {
            ButtonUpdate(function =
            { //Navigating to home
                navController.navigate(route = Screens.Home.screen)

                                    }, texto = "CANCELAR")
            ButtonUpdate(
                function =
                { viewModel.updatedMCQuestion.id?.let {
                    viewModel.updateMCQuestion(viewModel.updatedMCQuestion, it)
                }
                    viewModel.updatedMCQuestion= Pregunta_opcion_multiple_Api()



                    //Answer correct
                    viewModel.updatedMCCAnswer.id?.let {
                        viewModel.updateMCAnswer(viewModel.updatedMCCAnswer, it)
                    }
                    viewModel.updatedMCCAnswer= Respuesta_opcion_multiple_api(correcta = true)



                    //Answer incorrect 1
                    viewModel.updatedMCIAnswer1.id?.let {
                        viewModel.updateMCAnswer(viewModel.updatedMCIAnswer1, it)
                    }
                    viewModel.updatedMCIAnswer1= Respuesta_opcion_multiple_api(correcta = false)



                    //Answer incorrect 2
                    viewModel.updatedMCIAnswer2.id?.let {
                        viewModel.updateMCAnswer(viewModel.updatedMCIAnswer2, it)
                    }
                    viewModel.updatedMCIAnswer2= Respuesta_opcion_multiple_api(correcta = false)


                    //Answer incorrect 3
                    viewModel.updatedMCIAnswer3.id?.let {
                        viewModel.updateMCAnswer(viewModel.updatedMCIAnswer3, it)
                    }
                    viewModel.updatedMCIAnswer3= Respuesta_opcion_multiple_api(correcta = false)

                    //Navigating to home
                    navController.navigate(route = Screens.Home.screen)











                                    },
                texto = "CONFIRMAR")
            ButtonUpdate(function =
            { viewModel.updatedMCQuestion= Pregunta_opcion_multiple_Api()
                viewModel.updatedMCCAnswer= Respuesta_opcion_multiple_api(correcta = true)
                viewModel.updatedMCIAnswer1= Respuesta_opcion_multiple_api(correcta = false)
                viewModel.updatedMCIAnswer2= Respuesta_opcion_multiple_api(correcta = false)
                viewModel.updatedMCIAnswer3= Respuesta_opcion_multiple_api(correcta = false)





            },
                texto = "AGREGAR OTRA PREGUNTA")


        }


    }
}

@Composable
fun TitleQuiz(action:String, modifier: Modifier,) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = action,fontWeight = FontWeight.ExtraBold, color = White, textAlign = TextAlign.Center, fontSize = 30.sp, style = FontChewy)
    }
}

@Composable
fun MCQuestionArea(modifier: Modifier,viewModel: LessonsViewModel){
    var QuestionValue by remember {
        mutableStateOf(viewModel.updatedMCQuestion.enunciado)
    }
    Column {
        Text(text = "Pregunta",fontWeight = FontWeight.ExtraBold, style = FontChewy, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp), color = DarkGreen, fontSize = 32.sp )

        TextField(value = QuestionValue, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {QuestionValue=it
                viewModel.updatedMCQuestion=viewModel.updatedMCQuestion.copy(enunciado = it)
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }


}

@Composable
fun MCCorrectAnswers(modifier: Modifier,  viewModel: LessonsViewModel){
    var AnswerValue by remember {
        mutableStateOf(viewModel.updatedMCCAnswer.descripcion)
    }
    Column {
        TextField(value = AnswerValue, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {AnswerValue=it
                viewModel.updatedMCCAnswer=viewModel.updatedMCCAnswer.copy(descripcion = it)
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}

@Composable
fun MCIncorrectAnswer1(modifier: Modifier,  viewModel: LessonsViewModel){
    var AnswerValue1 by remember {
        mutableStateOf(viewModel.updatedMCIAnswer1.descripcion)
    }
    Column {
        TextField(value = AnswerValue1, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {AnswerValue1=it
                viewModel.updatedMCIAnswer1=viewModel.updatedMCIAnswer1.copy(descripcion = it)
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}

@Composable
fun MCIncorrectAnswer2(modifier: Modifier, viewModel: LessonsViewModel){
    var AnswerValue2 by remember {
        mutableStateOf(viewModel.updatedMCIAnswer2.descripcion)
    }
    Column {
        TextField(value = AnswerValue2, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {AnswerValue2=it
                viewModel.updatedMCIAnswer2=viewModel.updatedMCIAnswer2.copy(descripcion = it)
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}

@Composable
fun MCIncorrectAnswer3(modifier: Modifier, viewModel: LessonsViewModel){
    var AnswerValue3 by remember {
        mutableStateOf(viewModel.updatedMCIAnswer3.descripcion)
    }
    Column {
        TextField(value = AnswerValue3, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {AnswerValue3=it
                viewModel.updatedMCIAnswer3=viewModel.updatedMCIAnswer3.copy(descripcion = it)
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}



@Composable
fun ButtonUpdateQuiz(function : ()->Unit,texto :String) {
    Button(onClick = { function() }, colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
       ) {
        Text(text = texto, color = White, style = FontChilanka )
    }
}

/*
@Composable
@Preview
fun UpdateQuizComponentPreview(){
    UpdateMCQuestionComponent("Agregar", viewModel = LessonsViewModel(application = Application()), navController)
}
*/
