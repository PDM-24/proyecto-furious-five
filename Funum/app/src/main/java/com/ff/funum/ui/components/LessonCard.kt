package com.ff.funum.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ff.funum.data.api.LessonAPI
import com.ff.funum.ui.screens.LessonsViewModel
import com.ff.funum.ui.screens.Screens
import com.ff.funum.ui.theme.Chewy
import com.ff.funum.ui.theme.Green2
import com.ff.funum.ui.theme.GreenTopics

@Composable
fun LessonCard(
    lessons: LessonAPI,
    viewModel: LessonsViewModel,
    onClick: () -> Unit,
    navController: NavController
) {
    var expandedLessonIndex by remember { mutableStateOf(-1) }
    lessons.leccion.forEachIndexed { index, lesson ->
        val isExpanded = expandedLessonIndex == index

        Card(
            modifier = Modifier
                .clickable {
                    expandedLessonIndex = if (isExpanded) -1 else index
                }
                .animateContentSize()
                .padding(8.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Green2)
                ) {
                    AsyncImage(
                        model = lesson.imagen,
                        contentDescription = "lesson image",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Leccion ${index + 1}", fontFamily = Chewy, fontSize = 24.sp)
                        Text(text = lesson.nombre)
                    }
                }
                if (isExpanded) {
                    Column(modifier = Modifier.background(GreenTopics)) {
                        lesson.topicList.forEachIndexed { topicIndex, topicAPI ->
                            Column(modifier = Modifier.clickable {
                                viewModel.saveDataFromSelectedTopic(topicAPI)
                                onClick()
                                viewModel.beginTopic(lesson.id, topicAPI.id)
                            }) {
                                Text(
                                    text = "Tema ${topicIndex + 1}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp, 4.dp),
                                    fontFamily = Chewy,
                                    fontSize = 28.sp
                                )
                                Text(
                                    text = topicAPI.nombre,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp, 4.dp)
                                )
                            }
                        }
                        if (lesson.lessonExamList.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        viewModel.auxUiState.lesson = lesson.id
                                        navController.navigate(route = "${Screens.Quiz.screen}/${lesson.lessonExamList.first()}")
                                    }
                            ) {
                                Text(
                                    text = "Quiz",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp, 4.dp),
                                    fontFamily = Chewy,
                                    fontSize = 28.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}