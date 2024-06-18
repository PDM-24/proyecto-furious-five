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
fun UpdateTopicScreen(accion : String, topic: TopicAPI=TopicAPI(id = "",imagen = listOf("")), idLesson:String, viewModel: LessonsViewModel) {
    viewModel.updatedTopic=topic
    Column(modifier = Modifier
        .fillMaxSize()
        .background(LightGreen)) {
        UpdateTopicComponent(accion,viewModel,idLesson,topic)
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UpdateTopicComponent(accion: String,viewModel: LessonsViewModel,idLesson: String,topic: TopicAPI){

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween ){
        if(accion==="Agregar"){
            TitleTopic(action = "Agregar tema",
                Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .weight(0.1f))
        }else if(accion==="Actualizar"){
            TitleTopic(action = "Actualizar tema",
                Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .weight(0.1f))
        }

        Topic(
            Modifier
                .weight(0.2f)
                .fillMaxSize(),
            viewModel
        )
        Content(
            Modifier
                .weight(0.5f)
                .fillMaxSize()
            ,viewModel
        )
        TopicImage(
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
            TopicPonderation(
                Modifier
                    .weight(0.5f)
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(),
                viewModel
            )
            TopicVisibility(
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
            ButtonUpdate(function = { viewModel.updatedTopic= TopicAPI() }, texto = "Cancelar")
            if(accion==="Agregar"){
                ButtonUpdate(function = {
                    viewModel.updatedTopic.id?.let { viewModel.updateTopic(viewModel.updatedTopic, idTopic = it, idLesson = idLesson, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50") }
                    if(viewModel.updatedTopic.visibility){
                        viewModel.updatedTopic.id?.let { viewModel.toggleTopicVisibility(idTopic = it, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50") }
                    }
                }, texto = "Agregar")
            }else if(accion==="Actualizar"){
                ButtonUpdate(function = {
                    viewModel.updatedTopic.id?.let { viewModel.updateTopic(viewModel.updatedTopic, idTopic = it, idLesson = idLesson, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50") }
                    if(viewModel.updatedTopic.visibility!=topic.visibility){
                        viewModel.updatedTopic.id?.let { viewModel.toggleTopicVisibility(idTopic = it, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTZhOTI1YWY0N2ZjMTI3YjkxMzI5YjUiLCJleHAiOjE3MTg2NDU1MzgsImlhdCI6MTcxNzM0OTUzOH0.w6BZb-nl7VdKbuwsvi8NCy3nUpVAbY-zn49b1-Tqz50") }
                    }
                }, texto = "Actualizar")
            }

        }


    }
}
@Composable
fun Topic(modifier: Modifier,viewModel: LessonsViewModel) {
    var topic by remember {
        mutableStateOf(viewModel.updatedTopic.nombre)
    }
    Column (
        modifier, verticalArrangement = Arrangement.Center){
        Text(text = "Tema",fontWeight = FontWeight.ExtraBold, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp), color = DarkGreen, fontSize = 25.sp )
        TextField(value = topic, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {
                topic=it
                viewModel.updatedTopic=viewModel.updatedTopic.copy(nombre = it)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}
@Composable
fun Content(modifier: Modifier,viewModel: LessonsViewModel){
    var content by remember {
        mutableStateOf(viewModel.updatedTopic.contenido)
    }
    Column (
        modifier, verticalArrangement = Arrangement.Center){
        Text(text = "Contenido",fontWeight = FontWeight.ExtraBold, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp), color = DarkGreen, fontSize = 25.sp)
        TextField(value = content, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {
                content=it
                viewModel.updatedTopic=viewModel.updatedTopic.copy(contenido = it)
            },modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 5.dp), maxLines = 20)
    }
}
@Composable
fun TopicImage(modifier: Modifier,viewModel: LessonsViewModel){
    var topicImage by remember {
        mutableStateOf(viewModel.updatedTopic.imagen[0])
    }
    Column (
        modifier, verticalArrangement = Arrangement.Center
    ){
        Text(text = "Url de la imagen",fontWeight = FontWeight.ExtraBold, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp), color = DarkGreen, fontSize = 25.sp)
        TextField(value = topicImage, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {topicImage=it
                viewModel.updatedTopic=viewModel.updatedTopic.copy(imagen = arrayListOf(it))
            },keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp),singleLine = true)
    }
}

@Composable
fun TopicVisibility(modifier: Modifier,viewModel: LessonsViewModel){
    var visible by remember {
        mutableStateOf(viewModel.updatedTopic.visibility)
    }
    Row (
        modifier, verticalAlignment = Alignment.CenterVertically){
        Text(text = "Visibilidad",fontWeight = FontWeight.ExtraBold,modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp), color = DarkGreen, fontSize = 15.sp)
        Switch(checked = visible , onCheckedChange ={visible=it
            viewModel.updatedTopic=viewModel.updatedTopic.copy(visibility = visible)
        }, colors = SwitchDefaults.colors(checkedTrackColor = DarkGreen, uncheckedTrackColor = GreenTopics, uncheckedBorderColor = Green, uncheckedThumbColor = Green, checkedBorderColor = Green, checkedThumbColor = Green) )
    }

}

@Composable
fun TopicPonderation(modifier: Modifier,viewModel: LessonsViewModel){
    var ponderation by remember {
        mutableStateOf(viewModel.updatedTopic.ponderacion.toString())
    }
    Row (
        modifier, verticalAlignment = Alignment.CenterVertically){
        Text(text = "Ponderacion", fontWeight = FontWeight.ExtraBold,modifier = Modifier
            .padding(horizontal = 7.8.dp, vertical = 5.dp), color = DarkGreen, fontSize = 15.sp)
        TextField(value = ponderation, colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            onValueChange = {
                if(it!=""){
                    if(it.toInt()<=100) {
                        ponderation = it
                        viewModel.updatedTopic =
                            viewModel.updatedTopic.copy(ponderacion = ponderation.toInt())
                    }else{
                        ponderation = "100"
                        viewModel.updatedTopic =
                            viewModel.updatedTopic.copy(ponderacion = ponderation.toInt())
                    }
                }else{
                    ponderation=it
                    viewModel.updatedTopic=viewModel.updatedTopic.copy(ponderacion = 0)

                }
            }, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), maxLines = 1 )
    }
}

@Composable
fun ButtonUpdate(function : ()->Unit,texto :String) {
    Button(onClick = { function() }, colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)) {
        Text(text = texto )
    }
}
@Composable
fun TitleTopic(action:String, modifier: Modifier,) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = action,fontWeight = FontWeight.ExtraBold, color = White, textAlign = TextAlign.Center, fontSize = 30.sp)
    }
}