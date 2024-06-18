package com.ff.funum.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.ff.funum.data.api.Lessons
import com.ff.funum.data.api.TopicAPI
import com.ff.funum.ui.screens.LessonsViewModel
import com.ff.funum.ui.theme.Black
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.Green
import com.ff.funum.ui.theme.GreenTopics
import com.ff.funum.ui.theme.LightGreen
import com.ff.funum.ui.theme.White


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UpdateLessonScreen(accion : String, lesson: Lessons=Lessons(id = ""), idLesson:String, viewModel: LessonsViewModel) {
    viewModel.updatedLesson=lesson
    Column(modifier = Modifier
        .fillMaxSize()
        .background(LightGreen)) {
        UpdateLessonComponent(accion,viewModel,idLesson,lesson)
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UpdateLessonComponent(accion: String,viewModel: LessonsViewModel,idLesson: String,lesson: Lessons){

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween ){
        if(accion==="Agregar"){
            TitleLesson(action = "Agregar leccion",
                Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .weight(0.1f))
        }else if(accion==="Actualizar"){
            TitleLesson(action = "Actualizar leccion",
                Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .weight(0.1f))
        }

        Lesson(
            Modifier
                .weight(0.2f)
                .fillMaxSize(),
            viewModel
        )
        LessonImage(
            Modifier
                .weight(0.2f)
                .fillMaxWidth()
            ,viewModel
        )
        Row(
            Modifier
                .weight(0.2f)
                .fillMaxSize()
                .padding(horizontal = 20.dp)) {
            LessonArea(
                Modifier
                    .weight(0.5f)
                    .fillMaxSize()
                ,viewModel
            )
            LessonVisibility(
                Modifier
                    .weight(0.5f)
                    .padding(horizontal = 10.dp)
                    .fillMaxSize()
                ,viewModel
            )
        }
        Row (
            Modifier
                .fillMaxWidth()
                .weight(0.1f),horizontalArrangement = Arrangement.SpaceAround) {
            ButtonUpdate(function = { viewModel.updatedLesson= Lessons() }, texto = "Cancelar")
            if(accion==="Agregar"){
                ButtonUpdateLesson(function = {
                    viewModel.updatedLesson.id?.let {
                        viewModel.updateLesson(viewModel.updatedLesson, it,token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50")
                    }
                    if(viewModel.updatedLesson.visibility){
                        viewModel.updatedLesson.id?.let { viewModel.toggleLessonVisibility(idLesson = it, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50") }
                    }
                    viewModel.updatedLesson= Lessons()
                }, texto = "Agregar")
            }else if(accion==="Actualizar"){
                ButtonUpdateLesson(function = {
                    viewModel.updatedLesson.id?.let {
                        viewModel.updateLesson(viewModel.updatedLesson, it,token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50")
                    }
                    if(viewModel.updatedLesson.visibility){
                        viewModel.updatedLesson.id?.let { viewModel.toggleLessonVisibility(idLesson = it, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50") }
                    }
                    viewModel.updatedLesson= Lessons()
                }, texto = "Actualizar")
            }

        }


    }
}
@Composable
fun Lesson(modifier: Modifier,viewModel: LessonsViewModel) {
    var lesson by remember {
        mutableStateOf(viewModel.updatedLesson.nombre)
    }
    Column (
        modifier, verticalArrangement = Arrangement.Center){
        Text(text = "Leccion",fontWeight = FontWeight.ExtraBold, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp), color = DarkGreen, fontSize = 25.sp )
        TextField(value = lesson, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {
                lesson=it
                viewModel.updatedLesson=viewModel.updatedLesson.copy(nombre = it)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonArea(modifier: Modifier,viewModel: LessonsViewModel){
    val areas = listOf(
        "",
        "Algebra",
        "Geometria",
        "Aritmetica"
    )
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(areas[0]) }



    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = "Selecciona un area")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }) {

            areas.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        selectedOption = option
                        isExpanded = false
                        viewModel.updatedLesson=viewModel.updatedLesson.copy(area_estudio = option)
                    })
            }


        }
    }
}
@Composable
fun LessonImage(modifier: Modifier,viewModel: LessonsViewModel){
    var lessonImage by remember {
        mutableStateOf(viewModel.updatedLesson.imagen)
    }
    Column (
        modifier, verticalArrangement = Arrangement.Center
    ){
        Text(text = "Url de la imagen",fontWeight = FontWeight.ExtraBold, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp), color = DarkGreen, fontSize = 25.sp)
        TextField(value = lessonImage, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {lessonImage=it
                viewModel.updatedLesson=viewModel.updatedLesson.copy(imagen = it)
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}

@Composable
fun LessonVisibility(modifier: Modifier,viewModel: LessonsViewModel){
    var visible by remember {
        mutableStateOf(viewModel.updatedLesson.visibility)
    }
    Row (
        modifier, verticalAlignment = Alignment.CenterVertically){
        Text(text = "Visibilidad",fontWeight = FontWeight.ExtraBold,modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp), color = DarkGreen, fontSize = 15.sp)
        Switch(checked = visible , onCheckedChange ={visible=it
            viewModel.updatedLesson=viewModel.updatedLesson.copy(visibility = visible)
        }, colors = SwitchDefaults.colors(checkedTrackColor = DarkGreen, uncheckedTrackColor = GreenTopics, uncheckedBorderColor = Green, uncheckedThumbColor = Green, checkedBorderColor = Green, checkedThumbColor = Green) )
    }

}


@Composable
fun ButtonUpdateLesson(function : ()->Unit,texto :String) {
    Button(onClick = { function() }, colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)) {
        Text(text = texto, color = White )
    }
}
@Composable
fun TitleLesson(action:String, modifier: Modifier,) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = action,fontWeight = FontWeight.ExtraBold, color = White, textAlign = TextAlign.Center, fontSize = 30.sp)
    }
}