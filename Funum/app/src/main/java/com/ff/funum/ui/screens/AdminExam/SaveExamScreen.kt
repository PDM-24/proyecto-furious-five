package com.ff.funum.ui.screens.AdminExam

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ff.funum.R
import com.ff.funum.data.api.AdminSaveExam
import com.ff.funum.data.api.TopicAPI
import com.ff.funum.data.listLessons
import com.ff.funum.ui.components.LoadingProgressDialog
import com.ff.funum.ui.screens.LessonsViewModel
import com.ff.funum.ui.screens.UiState
import com.ff.funum.ui.theme.Black
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.Chilanka
import com.ff.funum.ui.theme.DarkGreen
import com.ff.funum.ui.theme.FunumTheme
import com.ff.funum.ui.theme.Green2
import com.ff.funum.ui.theme.White

@Composable
fun SaveExamScreen(
    viewModel: LessonsViewModel,
    innerPadding: Dp = 0.dp,
    navController: NavController,
    leccionId: String?
){
    val uiState = viewModel.uiState.collectAsState()
    val newExam = remember {
        mutableStateOf(AdminSaveExam(idLeccion = leccionId))
    }
    val tema = remember {
        mutableStateOf("")
    }

    val temas = remember {
        viewModel.getLessonTopics(leccionId)
    }

    when(uiState.value){
        is UiState.Error -> {
            val message = (uiState.value as UiState.Error).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            viewModel.setStateToReady()
        }
        UiState.Loading -> {
            LoadingProgressDialog()
        }
        UiState.Ready -> {}
        is UiState.Success -> {}
    }

    FunumTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.secondarySecond)
                ), verticalArrangement = Arrangement.SpaceBetween ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Green2)
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = "Crear examen", fontFamily = Chewy, fontSize = 40.sp, color = White)
            }

            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.SpaceAround)
            {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = newExam.value.nombre,
                    onValueChange = {newExam.value = newExam.value.copy(nombre = it)},
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
                    label = { Text(text = "Nombre del examen")})
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(128.dp),
                    value = newExam.value.descripcion,
                    onValueChange = {newExam.value = newExam.value.copy(descripcion = it)},
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
                    label = { Text(text = "Descripcion")}
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = newExam.value.ponderacion,
                    onValueChange = {newExam.value = newExam.value.copy(ponderacion = it)},
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
                    label = { Text(text = "Ponderacion")}
                )
                DropDownMenu(
                    selectedValue = tema.value,
                    options = temas.toList(),
                    label = "Tema",
                    onValueChangedEvent = {tema.value = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            Row (modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(30.dp), horizontalArrangement = Arrangement.Center){
                Button(
                    onClick = {
                        viewModel.saveExam(
                            newExam = newExam.value,
                            tema = tema.value,
                            temas = temas
                        )
                    }, colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.secondaryFirst)
                )) {
                    Text(modifier = Modifier.padding(7.dp), text = "Guardar", fontSize = 17.sp, fontFamily = Chilanka, color = White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    selectedValue: String,
    options: List<TopicAPI>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(unfocusedContainerColor = White, focusedContainerColor = White, focusedTextColor = Black, unfocusedTextColor = Black, cursorColor = Black, focusedIndicatorColor = DarkGreen),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.nombre) },
                    onClick = {
                        expanded = false
                        onValueChangedEvent(option.nombre)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SaveExamScreenPrev() {
    //SaveExamScreen()
}